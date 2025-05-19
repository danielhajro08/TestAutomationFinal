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

import globals.GlobalVariables;
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
    GlobalVariables globalVariables;

@BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce.tealiumdemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        homepagePage = new HomepagePage(driver);
        registerPage = new RegisterPage(driver);
        dashboardPage = new DashboardPage(driver);
        logInPage = new LogInPage(driver);
        womenPage = new WomenPage(driver);
        salePage = new SalePage(driver);
        menPage = new MenPage(driver);
        wishlistPage = new WishlistPage(driver);
        productInfoPage = new ProductInfoPage(driver);
        cartPage = new CartPage(driver);
        globalVariables = new GlobalVariables(driver);
    }

    @AfterClass
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test1(){
        homepagePage.clickAccountButton();
        registerPage =homepagePage.clickRegisterButton();
        registerPage.checkPageTitle();
        registerPage.fillRegisterFields(globalVariables.first, globalVariables.last, globalVariables.email, globalVariables.pass, globalVariables.pass);
        dashboardPage=registerPage.clickRegisterButton();
        dashboardPage.matchSuccessMessage();
        dashboardPage.clickAccountButton();
        dashboardPage.clickLogOut();
    }

    @Test
    public void test2(){
        dashboardPage =globalVariables.logIn();
        dashboardPage.checkWelcomeMessage(globalVariables.first, globalVariables.last);
        dashboardPage.clickAccountButton();
        dashboardPage.clickLogOut();
    }
    
    @Test
    public void test3(){
        dashboardPage =globalVariables.logIn();
        womenPage=dashboardPage.hoverOnWomen();
        womenPage.checkDifferenceInStyle();
    }

    @Test 
    public void test4(){
        dashboardPage =globalVariables.logIn();
        salePage= dashboardPage.hoverOnSale();
        salePage.verifyOldPriceExistsInAllItems();
        salePage.verifySpecialPriceExistsInAllItems();
        salePage.checkOldPriceStyle();
        salePage.checkSpecialPriceStyle();
    }

    @Test
    public void test5(){
        dashboardPage =globalVariables.logIn();
        menPage = dashboardPage.hoverOnMale();
        menPage.clickBlackColorOption();
        menPage.checkColorIsSelected();
        menPage.clickOnPrice();
        menPage.checkNoOfElement();
        menPage.checkPricesOfElements();
    }

    @Test
    public void test6(){
        dashboardPage =globalVariables.logIn();
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
        wishlistPage =globalVariables.wishlist();
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
        cartPage =globalVariables.cart();
        cartPage.deleteFirstAndCheck();
        cartPage.deleteItem();
        cartPage.checkEmptyMessage();
    }
}
