package pageMethods;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.WishlistPageElement;

public class WishlistPage {
WebDriver driver;
    WishlistPageElement wishlistPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        wishlistPageElement = new WishlistPageElement(driver);
    }

    public void successToWishlist(){
        wait.until(ExpectedConditions.visibilityOf(wishlistPageElement.success));
        Assert.assertTrue(wishlistPageElement.success.isDisplayed(),"Success Message not visible");
    }

    public WomenPage goBackToWomenPage(){
        driver.navigate().back();
        return new WomenPage(driver);
    }



    public ProductInfoPage addCart(){
        wait.until(ExpectedConditions.visibilityOf(wishlistPageElement.addFirstToCart));
        wishlistPageElement.addFirstToCart.click();;
        return new ProductInfoPage(driver);
    }
}
