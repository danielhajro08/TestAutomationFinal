package pageMethods;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.WomenPageElement;

public class WomenPage {
    WebDriver driver;
    WomenPageElement womenPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public WomenPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        womenPageElement = new WomenPageElement(driver);
    }

    public String getStyleUnhovered() {
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.firstWomenElement));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.firstWomenElement);
        String styleUnhover = womenPageElement.firstWomenElement.getCssValue("border-color");
        return styleUnhover;
    }

    public String getStyleHovered() {
        Actions hover = new Actions(driver);
        hover.moveToElement(womenPageElement.firstWomenElement).perform();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.firstWomenElement);

        String styleHover = womenPageElement.firstWomenElement.getCssValue("border-color");
        return styleHover;
    }


    // TESTI 6
    
    
    public void sortByPrice() {
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.selectSorting));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.selectSorting);
        womenPageElement.selectSorting.click();
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.optionPrice));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.optionPrice);
        womenPageElement.optionPrice.click();
    }

    public void checkPricesAreSorted(){
        wait.until(ExpectedConditions.visibilityOfAllElements(womenPageElement.pricesSorted));
        int length= womenPageElement.pricesSorted.size();
        for(int i=0;i<length-1;i++){
            try {
                WebElement el1 = womenPageElement.pricesSorted.get(i);
                WebElement el2 = womenPageElement.pricesSorted.get(i+1);
                String elString1 = el1.getText().replace("$", "");
                String elString2 = el2.getText().replace("$", "");
                double el1Formatted = Double.parseDouble(elString1);
                double el2Formatted = Double.parseDouble(elString2);
                System.out.println("now comparing : " + el1Formatted + " with index: " + i + " and : " + el2Formatted + " with index" + (i+1));
                Assert.assertTrue(el2Formatted >= el1Formatted,"Sort doesnt work ok");

            } catch (Exception e) {
                Assert.fail("Not working at value: " + i, e);
            }
        }
    }

    public WishlistPage addFirstToWishlist(){
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.firstToWishlist));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.firstToWishlist);
        womenPageElement.firstToWishlist.click();
        return new WishlistPage(driver);
    }

    public WishlistPage addSecondToWishlist(){
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.secondToWishlist));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.secondToWishlist);
        womenPageElement.secondToWishlist.click();
        return new WishlistPage(driver);
    }

    public WishlistPage checkWishlistItems(){
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.accountButton));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", womenPageElement.accountButton);
        womenPageElement.accountButton.click();
        wait.until(ExpectedConditions.visibilityOf(womenPageElement.wishlistAnchor));
        String wishMessage = womenPageElement.wishlistAnchor.getText();
        Assert.assertTrue(wishMessage.equals("My Wishlist (2 items)"), "Doesnt match");
        womenPageElement.wishlistAnchor.click();
        return new WishlistPage(driver);
    }

    public void checkDifferenceInStyle(){
        String beforeHover= getStyleUnhovered();
        String afterHover= getStyleHovered();
        Assert.assertNotEquals(beforeHover, afterHover);
    }
}
