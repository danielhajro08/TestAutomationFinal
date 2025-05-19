package globals;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

public class GlobalVariables {
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

   public GlobalVariables(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.js = (JavascriptExecutor) driver;
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
}

    public String first = "Andi";
    public String last = "Hoxha";
    public String email = "aaanndi12345hoxha@example.com";
    public String pass = "asdqwe123";

    public DashboardPage logIn(){
        homepagePage.clickAccountButton();
        logInPage=homepagePage.clickLogIn();
        logInPage.closeOpt();
        logInPage.fillLogIn(email, pass);
        dashboardPage=logInPage.clickSubmit();
        return new DashboardPage(driver);
    }

    public WishlistPage wishlist(){
        logIn();
        dashboardPage.clickAccountButton();
        wishlistPage = dashboardPage.clickWishlist();
        return new WishlistPage(driver);
    }

    public CartPage cart(){
        logIn();
        dashboardPage.clickAccountButton();
        cartPage = dashboardPage.clickCart();
        return new CartPage(driver);
    }
}


