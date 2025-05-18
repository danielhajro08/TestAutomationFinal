package pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPageElement {
     WebDriver driver;
    public LogInPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "pass")
    public WebElement password;

    @FindBy(id = "send2")
    public WebElement submitLogIn;

    @FindBy(id = "privacy_pref_optin")
    public WebElement opt_in;

    @FindBy(id = "consent_prompt_submit")
    public WebElement submit_opt;
}
