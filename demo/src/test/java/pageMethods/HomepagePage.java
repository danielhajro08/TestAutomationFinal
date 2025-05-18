package pageMethods;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageElement.HomepageElement;

public class HomepagePage {
    WebDriver driver;
    HomepageElement homepageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public HomepagePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        homepageElement = new HomepageElement(driver);
    }

    public void clickAccountButton(){
        homepageElement.accountButton.click();
    }

    public RegisterPage clickRegisterButton(){
        wait.until(ExpectedConditions.elementToBeClickable(homepageElement.registerButton)).click();
        return new RegisterPage(driver);
    }

    public LogInPage clickLogIn(){
         js.executeScript("arguments[0].scrollIntoView({block: 'center'});",homepageElement.logInElement);
        wait.until(ExpectedConditions.visibilityOf(homepageElement.logInElement)).click();
        return new LogInPage(driver);
    }

}
