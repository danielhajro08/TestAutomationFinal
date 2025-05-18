package pageMethods;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageElement.ProductInfoPageElement;

public class ProductInfoPage {
WebDriver driver;
    ProductInfoPageElement productInfoPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        productInfoPageElement = new ProductInfoPageElement(driver);
    }

    public void chooseBlackColor(){
        wait.until(ExpectedConditions.visibilityOf(productInfoPageElement.blackColorImg));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productInfoPageElement.blackColorImg);
        productInfoPageElement.blackColorImg.click();
    } 

    public void chooseWhiteColor(){
        wait.until(ExpectedConditions.visibilityOf(productInfoPageElement.whiteColorImg));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productInfoPageElement.whiteColorImg);
        productInfoPageElement.whiteColorImg.click();
    }

    public void chooseMSize(){
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productInfoPageElement.mSize);
        productInfoPageElement.mSize.click();
    }

    public CartPage addToCart(){
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productInfoPageElement.addToCart);
        productInfoPageElement.addToCart.click();
        return new CartPage(driver);
    }

}
