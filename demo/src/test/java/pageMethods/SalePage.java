package pageMethods;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.SalePageElement;

public class SalePage {
    WebDriver driver;
    SalePageElement salePageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public SalePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        salePageElement = new SalePageElement(driver);
    }

    public void verifyOldPriceExistsInAllItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(salePageElement.saleItemList));
        List<WebElement> saleItemList = salePageElement.saleItemList;

        int index = 0;
        for (WebElement item : saleItemList) {
            try {
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
                WebElement oldPriceElement = item.findElement(By.cssSelector("p.old-price"));
                Assert.assertTrue(oldPriceElement.isDisplayed(), "Old price is not displayed.");
                // System.out.println("Old Price at index " + index + " checked successfully.");
            } catch (NoSuchElementException e) {
                Assert.fail("Missing <p class='old-price'> in sale item at index: " + index);
            }
            index++;
        }
    }

    public void verifySpecialPriceExistsInAllItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(salePageElement.saleItemList));
        List<WebElement> saleItemList = salePageElement.saleItemList;
        int index = 0;
        for (WebElement item : saleItemList) {
            try {
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", item);
                WebElement specialPriceElement = item.findElement(By.cssSelector("p.special-price"));
                Assert.assertTrue(specialPriceElement.isDisplayed(), "Special Price is not displayed.");
                // System.out.println("Special Price at index " + index + " checked
                // successfully.");
            } catch (NoSuchElementException e) {
                Assert.fail("Missing <p class='special-price'> in sale item at index: " + index);
            }
            index++;
        }
    }

    public void checkOldPriceStyle() {
        int index = 0;
        for (WebElement element : salePageElement.old_price) {
            try {
                String color = element.getCssValue("color");
                String decoration = element.getCssValue("text-decoration");
                Assert.assertEquals(color, "rgba(160, 160, 160, 1)", "Color doesnt match");
                Assert.assertTrue(decoration.contains("line-through"), "Expected line-through but got: " + decoration + " at index: " + index);
            } catch (NoSuchElementException e) {
                Assert.fail("Missing styles in sale item at index: " + index);
            }
            index++;
        }
    }

    public void checkSpecialPriceStyle() {
        int index = 0;
        for (WebElement element : salePageElement.special_price) {
            try {
                String color = element.getCssValue("color");
                String decoration = element.getCssValue("text-decoration");
                Assert.assertEquals(color, "rgba(51, 153, 204, 1)", "Color doesnt match");
                Assert.assertTrue(decoration.contains("none"), "Expected none but got: " + decoration + " at index: " + index);
            } catch (NoSuchElementException e) {
                Assert.fail("Missing styles in sale item at index: " + index);
            }
            index++;
            //System.out.println(index);
        }
    }

}
