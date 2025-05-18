package pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomepageElement {
    WebDriver driver;

     public HomepageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "ACCOUNT")
    public WebElement accountButton;

    @FindBy(xpath = "//a[@title='Register']")
    public WebElement registerButton;

    @FindBy(xpath = "//a[@title='Log In']")
    public WebElement logInElement;
    
}
