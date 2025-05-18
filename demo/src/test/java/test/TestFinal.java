package test;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageMethods.CartPage;
import pageMethods.DashboardPage;
import pageMethods.HomepagePage;
import pageMethods.LogInPage;
import pageMethods.MenPage;
import pageMethods.ProductInfoPage;
import pageMethods.RegisterPage;
import pageMethods.SalePage;
import pageMethods.WishlistPage;
import pageMethods.WomenPage;

public class TestFinal {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    HomepagePage homepagePage;
    RegisterPage registerPage;
    DashboardPage dashboardPage;
    LogInPage logInPage;
    WomenPage womenPage;
    SalePage salePage;
    MenPage menPage;
    WishlistPage wishlistPage;
    ProductInfoPage productInfoPage;
    CartPage cartPage;

@BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce.tealiumdemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        homepagePage = new HomepagePage(driver);
    }

    @AfterClass
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    String first = "Andi";
    String last = "Hoxha";
    String email = "andi12345hoxha@example.com";
    String pass = "asdqwe123";

    public void logIn(){
        homepagePage.clickAccountButton();
        logInPage=homepagePage.clickLogIn();
        logInPage.closeOpt();
        logInPage.fillLogIn(email, pass);
        dashboardPage=logInPage.clickSubmit();
    }

    public void wishlist(){
        logIn();
        dashboardPage.clickAccountButton();
        wishlistPage = dashboardPage.clickWishlist();
    }

    public void cart(){
        logIn();
        dashboardPage.clickAccountButton();
        cartPage = dashboardPage.clickCart();
    }

    @Test
    public void test1(){
        homepagePage.clickAccountButton();
        registerPage =homepagePage.clickRegisterButton();
        Assert.assertEquals(registerPage.checkPageTitle(), "create an account","Equals");
        registerPage.fillRegisterFields(first, last, email, pass, pass);
        dashboardPage=registerPage.clickRegisterButton();
        Assert.assertEquals(dashboardPage.matchSuccessMessage(), "Thank you for registering with Tealium Ecommerce.", "Matched");
        dashboardPage.clickAccountButton();
        dashboardPage.clickLogOut();
    }

    @Test
    public void test2(){
        homepagePage.clickAccountButton();
        logInPage=homepagePage.clickLogIn();
        logInPage.fillLogIn(email, pass);
        dashboardPage=logInPage.clickSubmit();
        Assert.assertEquals(dashboardPage.getWelcomeMessage().toLowerCase(), "welcome, " + first.toLowerCase() + " " + last.toLowerCase() + "!","Matched");
        dashboardPage.clickAccountButton();
        dashboardPage.clickLogOut();
    }
    
    @Test
    public void test3(){
        logIn();
        womenPage=dashboardPage.hoverOnWomen();
        String beforeHover= womenPage.getStyleUnhovered();
        String afterHover= womenPage.getStyleHovered();
        Assert.assertNotEquals(beforeHover, afterHover);

    }

    @Test 
    public void test4(){
        logIn();
        salePage= dashboardPage.hoverOnSale();
        salePage.verifyOldPriceExistsInAllItems();
        salePage.verifySpecialPriceExistsInAllItems();
        salePage.checkOldPriceStyle();
        salePage.checkSpecialPriceStyle();
    }

    @Test
    public void test5(){
        logIn();
        menPage = dashboardPage.hoverOnMale();
        menPage.clickBlackColorOption();
        menPage.checkColorIsSelected();
        menPage.clickOnPrice();
        menPage.checkNoOfElement();
        menPage.checkPricesOfElements();
    }

    @Test
    public void test6(){
        logIn();
        womenPage = dashboardPage.hoverOnWomen();
        womenPage.sortByPrice();
        womenPage.checkPricesAreSorted();
        wishlistPage = womenPage.addFirstToWishlist();
        wishlistPage.successToWishlist();
        womenPage = wishlistPage.goBackToWomenPage();
        wishlistPage = womenPage.addSecondToWishlist();
        wishlistPage.successToWishlist();
        womenPage = wishlistPage.goBackToWomenPage();
        wishlistPage = womenPage.checkWishlistItems();
    }

    @Test
    public void test7(){
        wishlist();
        productInfoPage = wishlistPage.addCart();
        productInfoPage.chooseBlackColor();
        productInfoPage.chooseMSize();
        cartPage = productInfoPage.addToCart();
        cartPage.clickAccountButton();
        wishlistPage = cartPage.clickWishlist();
        productInfoPage = wishlistPage.addCart();
        productInfoPage.chooseWhiteColor();
        productInfoPage.chooseMSize();
        cartPage = productInfoPage.addToCart();
        cartPage.updateQuantity();
        cartPage.checkCosts();
    }

    @Test
    public void test8(){
        cart();
        cartPage.deleteFirstAndCheck();
        cartPage.deleteItem();
        cartPage.checkEmptyMessage();
    }
}
