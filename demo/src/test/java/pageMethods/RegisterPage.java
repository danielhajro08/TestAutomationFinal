package pageMethods;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.RegisterPageElement;

public class RegisterPage {
   WebDriver driver;
    RegisterPageElement registerPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        registerPageElement = new RegisterPageElement(driver);
    }

    public void checkPageTitle(){
        wait.until(ExpectedConditions.visibilityOf(registerPageElement.pageTitle));
        String title = registerPageElement.pageTitle.getText().toLowerCase();
        String titleExp = "create an account";
        Assert.assertEquals(title, titleExp,"Didnt match");
    }

   public void fillRegisterFields(String firstnameVal, String lastnameVal, String emailVal, String passwordVal, String confirmPassVal) {
    wait.until(ExpectedConditions.visibilityOf(registerPageElement.firstname));
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});",registerPageElement.firstname);
    registerPageElement.firstname.clear();
    registerPageElement.firstname.sendKeys(firstnameVal);

    js.executeScript("arguments[0].scrollIntoView({block: 'center'});",registerPageElement.lastname);
    registerPageElement.lastname.clear();
    registerPageElement.lastname.sendKeys(lastnameVal);

    js.executeScript("arguments[0].scrollIntoView({block: 'center'});",registerPageElement.email);
    registerPageElement.email.clear();
    registerPageElement.email.sendKeys(emailVal);

    js.executeScript("arguments[0].scrollIntoView({block: 'center'});",registerPageElement.password);
    registerPageElement.password.clear();
    registerPageElement.password.sendKeys(passwordVal);

    js.executeScript("arguments[0].scrollIntoView({block: 'center'});",registerPageElement.confirmPass);
    registerPageElement.confirmPass.clear();
    registerPageElement.confirmPass.sendKeys(confirmPassVal);
}

public DashboardPage clickRegisterButton(){
    js.executeScript("arguments[0].scrollIntoView({block:'center'});", registerPageElement.registerButton);
    registerPageElement.registerButton.click();
    return new DashboardPage(driver);
}

}
