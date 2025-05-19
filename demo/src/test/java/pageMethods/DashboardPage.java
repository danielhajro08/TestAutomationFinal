package pageMethods;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import globals.GlobalVariables;
import pageElement.DashboardPageElement;


public class DashboardPage {
    WebDriver driver;
    DashboardPageElement dashboardPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;
    GlobalVariables globalVariables;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        dashboardPageElement = new DashboardPageElement(driver);
    }

    

    public void matchSuccessMessage(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.success));
        String successString = dashboardPageElement.success.getText();
        String expectedString = "Thank you for registering with Tealium Ecommerce.";
        Assert.assertEquals(successString, expectedString, "Didnt match");
    }

    public void clickAccountButton(){
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.accountButton);
        dashboardPageElement.accountButton.click();
    } 

    public void clickLogOut(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.logOut)).click();
    }

    public WishlistPage clickWishlist(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.wishlist)).click();
        return new WishlistPage(driver);
    }

    public CartPage clickCart(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.cart)).click();
        return new CartPage(driver);
    }

    public String getWelcomeMessage(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.welcomeMessage));
        String welcomeString = dashboardPageElement.welcomeMessage.getText();
        return welcomeString;
    }

   public void checkWelcomeMessage(String firstName, String lastName){
    String expected = "welcome, " + firstName.toLowerCase() + " " + lastName.toLowerCase() + "!";
    Assert.assertEquals(getWelcomeMessage().toLowerCase(), expected, "Matched");
}


    public WomenPage hoverOnWomen(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.womenSelectHover));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.womenSelectHover);
        Actions hover = new Actions(driver);
        hover.moveToElement(dashboardPageElement.womenSelectHover).perform();
        
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.allWomenSelect));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.allWomenSelect);
        dashboardPageElement.allWomenSelect.click();
        return new WomenPage(driver);
    }

    public SalePage hoverOnSale(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.saleSelectHover));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.saleSelectHover);
        Actions hover = new Actions(driver);
        hover.moveToElement(dashboardPageElement.saleSelectHover).build().perform();
        
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.allSaleSelect));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.allSaleSelect);
        dashboardPageElement.allSaleSelect.click();
        return new SalePage(driver);
    }
   
    public MenPage hoverOnMale(){
        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.menSelectHover));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.menSelectHover);
        Actions hover = new Actions(driver);
        hover.moveToElement(dashboardPageElement.menSelectHover).perform();

        wait.until(ExpectedConditions.visibilityOf(dashboardPageElement.allMenSelect));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});",dashboardPageElement.allMenSelect);
        dashboardPageElement.allMenSelect.click();
        return new MenPage(driver);

    }

}
