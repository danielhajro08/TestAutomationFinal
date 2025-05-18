package pageMethods;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.MenPageElement;

public class MenPage {
    WebDriver driver;
    MenPageElement menPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public MenPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        menPageElement = new MenPageElement(driver);
    }

    public void clickBlackColorOption() {
        wait.until(ExpectedConditions.visibilityOf(menPageElement.blackColorSelect));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", menPageElement.blackColorSelect);

        menPageElement.blackColorSelect.click();
    }

    public void checkColorIsSelected() {
        int size = menPageElement.blackColorCheckList.size();

        for (int i = 0; i < size; i++) {
            int attempts = 0;
            while (attempts < 2) {
                try {
                    List<WebElement> blackList = menPageElement.blackColorCheckList;
                    wait.until(ExpectedConditions.visibilityOfAllElements(blackList));
                    WebElement element = blackList.get(i);
                    wait.until(ExpectedConditions.visibilityOf(element));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                    String selectedBorder = element.getCssValue("border-color");
                    Assert.assertEquals(selectedBorder, "rgb(51, 153, 204)", "Color doesn't match at index " + i);
                    break;
                } catch (StaleElementReferenceException e) {
                    attempts++;
                    if (attempts == 2)
                        Assert.fail("Stale element at index: " + i);
                }
            }
        }
    }

    public void clickOnPrice() {
        wait.until(ExpectedConditions.visibilityOf(menPageElement.priceValueClick));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", menPageElement.priceValueClick);

        menPageElement.priceValueClick.click();
    }

    public void checkNoOfElement() {
        int size = menPageElement.elementsAfterClick.size();
        int index = 0;
        for (int i=0;i<size;i++) {
            int attempts = 0;
            while (attempts < 2) {
                try {
                    List<WebElement> noList = menPageElement.elementsAfterClick;
                    wait.until(ExpectedConditions.visibilityOfAllElements(noList));
                    WebElement element = noList.get(i);
                    wait.until(ExpectedConditions.visibilityOf(element));
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                    Assert.assertTrue(element.isDisplayed(),"Element isnt shown");
                    break;
                } catch (NoSuchElementException e) {
                    Assert.fail("Didnt found element : " + index);
                } catch (StaleElementReferenceException e) {
                    attempts++;
                    if (attempts == 2)
                        Assert.fail("Stale element at index: ");
                }
            }
            index++;
        }
        Assert.assertEquals(index, 3, "Doesnt Match");
    }

    public void checkPricesOfElements() {
        wait.until(ExpectedConditions.visibilityOfAllElements(menPageElement.prices));
        int index = 0;
        for (WebElement element : menPageElement.prices) {
            try {
                String price = element.getText().replace("$", "");
                double formattedPrice = Double.parseDouble(price);
                Assert.assertTrue(formattedPrice > 0.00 && formattedPrice < 90.00,
                        "Price isn't in criteria at index " + index);
            } catch (Exception e) {
                Assert.fail("Not working at value: " + index, e);
            }
            index++;
        }
    }
}
