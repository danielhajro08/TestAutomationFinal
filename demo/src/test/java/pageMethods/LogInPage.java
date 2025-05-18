package pageMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.LogInPageElement;

public class LogInPage {
    WebDriver driver;
    LogInPageElement logInPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        logInPageElement = new LogInPageElement(driver);
    }

    public void closeOpt() {
    int attempts = 0;
    while (attempts < 2) {
        try {
            WebElement optIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("privacy_pref_optin"))); 
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", optIn);
            optIn.click();

            WebElement submitOpt = wait.until(ExpectedConditions.elementToBeClickable(By.id("consent_prompt_submit"))); 
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitOpt);
            submitOpt.click();
            break;
        } catch (StaleElementReferenceException e) {
            attempts++;
            if (attempts == 2) {
                Assert.fail("StaleElementReferenceException in closeOpt()");
            }
        }
    }
}


    public void fillLogIn(String emailVal, String passVal) {
        wait.until(ExpectedConditions.visibilityOf(logInPageElement.email));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", logInPageElement.email);
        logInPageElement.email.clear();
        logInPageElement.email.sendKeys(emailVal);

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", logInPageElement.password);
        logInPageElement.password.clear();
        logInPageElement.password.sendKeys(passVal);
    }

    public DashboardPage clickSubmit(){
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", logInPageElement.submitLogIn);
        logInPageElement.submitLogIn.click();
        return new DashboardPage(driver);
    }

}
