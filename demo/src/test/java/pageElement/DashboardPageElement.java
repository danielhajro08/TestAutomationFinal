package pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageElement {
     WebDriver driver;
    public DashboardPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="success-msg")
    public WebElement success;

    @FindBy(linkText = "ACCOUNT")
    public WebElement accountButton;

    @FindBy(xpath = "//a[@title='Log Out']")
    public WebElement logOut;

    @FindBy(xpath = "//a[contains(text(),'Wishlist')]")
    public WebElement wishlist;

    @FindBy(xpath = "//a[contains(text(),'My Cart')]")
    public WebElement cart;
    
    @FindBy(xpath = "//p[@class='welcome-msg']")
    public WebElement welcomeMessage;

    @FindBy(xpath = "//a[@class='level0 has-children'][contains(text(),'Women')]")
    public WebElement womenSelectHover;

    @FindBy (xpath = "//a[@class='level1'][contains(text(),'All Women')]")
    public WebElement allWomenSelect;

    @FindBy(xpath = "//a[@class='level0 has-children'][contains(text(),'Sale')]/..")
    public WebElement saleSelectHover;

    @FindBy (xpath = "//a[@class='level1'][contains(text(),'Sale')]")
    public WebElement allSaleSelect;

    @FindBy(xpath = "//a[@class='level0 has-children'][contains(text(),'Men')]")
    public WebElement menSelectHover;

    @FindBy (xpath = "//a[@class='level1'][contains(text(),'All Men')]")
    public WebElement allMenSelect;

}
