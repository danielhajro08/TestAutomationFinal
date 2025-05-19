package test;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import globals.BaseTest;
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

public class TestFinal extends BaseTest{
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
public void test1() {
    test = extent.createTest("Register and Logout Test");

    try {
        homepagePage.clickAccountButton();
        test.pass("Clicked account button");

        registerPage = homepagePage.clickRegisterButton();
        test.pass("Clicked register button");

        registerPage.checkPageTitle();
        test.pass("Checked page title");

        registerPage.fillRegisterFields(globalVariables.first, globalVariables.last, globalVariables.email, globalVariables.pass, globalVariables.pass);
        test.pass("Filled register form");

        dashboardPage = registerPage.clickRegisterButton();
        test.pass("Submitted registration");

        dashboardPage.matchSuccessMessage();
        test.pass("Matched success message");

        dashboardPage.clickAccountButton();
        dashboardPage.clickLogOut();
        test.pass("Logged out");

        // Mark final test result
        test.pass("Test completed successfully");
    } catch (Exception e) {
        test.fail("Test failed with error: " + e.getMessage());
        e.printStackTrace();
        throw e; // Optional: rethrow to fail the TestNG test too
    }
}

    @Test
    public void test2(){
        test = extent.createTest("Log In Check Test");
        
        dashboardPage =globalVariables.logIn();
        test.pass("Log in Succes");

        dashboardPage.checkWelcomeMessage(globalVariables.first, globalVariables.last);
        test.pass("Welcome Message Shown");

        dashboardPage.clickAccountButton();
        test.pass("Account Button Clicked");

        dashboardPage.clickLogOut();
        test.pass("Logged Out");
    }
    
    @Test
    public void test3(){
        test = extent.createTest("Hover Check");

        dashboardPage =globalVariables.logIn();
        test.pass("Log In Succes");

        womenPage=dashboardPage.hoverOnWomen();
        test.pass("Hover Success");

        womenPage.checkDifferenceInStyle();
        test.pass("Style Changes Visible");
    }

    @Test 
    public void test4(){
        test = extent.createTest("Check Sale Style");

        dashboardPage =globalVariables.logIn();
        test.pass("Log In Success");

        salePage= dashboardPage.hoverOnSale();
        test.pass("Sale Page Opened");
        
        salePage.verifyOldPriceExistsInAllItems();
        test.pass("Verified Old Price Shown");

        salePage.verifySpecialPriceExistsInAllItems();
        test.pass("Verified Special Price Shown");

        salePage.checkOldPriceStyle();
        test.pass("Checked Old Price Style");

        salePage.checkSpecialPriceStyle();
        test.pass("Checked Special Price Style");
    }

    @Test
    public void test5(){
        test = extent.createTest("Check Men Style");

        dashboardPage =globalVariables.logIn();
        test.pass("Success Log In");

        menPage = dashboardPage.hoverOnMale();
        test.pass("Success Open Men Section");

        menPage.clickBlackColorOption();
        test.pass("Black Color Option Selected");

        menPage.checkColorIsSelected();
        test.pass("Color Style for Selected Elements shown");

        menPage.clickOnPrice();
        test.pass("Checked Prices");

        menPage.checkNoOfElement();
        test.pass("Checked Number Of Elements");

        menPage.checkPricesOfElements();
        test.pass("Checked Prices");
    }

    @Test
    public void test6(){
        test = extent.createTest("Check Women Filters");

        dashboardPage =globalVariables.logIn();
        test.pass("Success Log In");

        womenPage = dashboardPage.hoverOnWomen();
        test.pass("Success Open Women Section");

        womenPage.sortByPrice();
        test.pass("Sorted By Price");

        womenPage.checkPricesAreSorted();
        test.pass("Prices are Sorted as Filter Says");

        wishlistPage = womenPage.addFirstToWishlist();
        test.pass("First Added to Wishlist");

        wishlistPage.successToWishlist();
        test.pass("Success Sent to Wishlist");

        womenPage = wishlistPage.goBackToWomenPage();
        test.pass("Scroll Back To Women");

        wishlistPage = womenPage.addSecondToWishlist();
        test.pass("Second Added to Wishlist");

        wishlistPage.successToWishlist();
        test.pass("Success Sent to Wishlist");

        womenPage = wishlistPage.goBackToWomenPage();
        test.pass("Scroll Back To Women");

        wishlistPage = womenPage.checkWishlistItems();
        test.pass("Checked Wishlist Message");
    }

    @Test
    public void test7(){
        test = extent.createTest("Adding Product to Cart");

        wishlistPage =globalVariables.wishlist();
        test.pass("Scrolled to Wishlist");

        productInfoPage = wishlistPage.addCart();
        test.pass("Cart Button Clicked");

        productInfoPage.chooseBlackColor();
        test.pass("Black Color Selectd");

        productInfoPage.chooseMSize();
        test.pass("Choosed Size M");

        cartPage = productInfoPage.addToCart();
        test.pass("Click Add to Cart");

        cartPage.clickAccountButton();
        test.pass("Account Button Clicked");

        wishlistPage = cartPage.clickWishlist();
        test.pass("Click Wishlist");

        productInfoPage = wishlistPage.addCart();
        test.pass("Cart Button Clicked");

        productInfoPage.chooseWhiteColor();
        test.pass("White Color Selectd");

        productInfoPage.chooseMSize();
        test.pass("Choosed Size M");

        cartPage = productInfoPage.addToCart();
        test.pass("Click Add to Cart");

        cartPage.updateQuantity();
        test.pass("Updated Quantity of Element");

        cartPage.checkCosts();
        test.pass("Checked Elements Prices");
    }

    @Test
    public void test8(){
        test = extent.createTest("Delete Cart List");

        cartPage =globalVariables.cart();
        test.pass("Scrolled to Cart");

        cartPage.deleteFirstAndCheck();
        test.pass("Deleted First Element");

        cartPage.deleteItem();
        test.pass("Deleted rest of Elements");

        cartPage.checkEmptyMessage();
        test.pass("Cart is Empty Message Found");
    }
}
