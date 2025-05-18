package pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPageElement {
    WebDriver driver;
    public ProductInfoPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@alt='Black']")
    public WebElement blackColorImg;

    @FindBy(xpath = "//img[@alt='White']")
    public WebElement whiteColorImg;

    @FindBy(xpath = "//a[@name='m']")
    public WebElement mSize;

    @FindBy(xpath = "(//button[@title='Add to Cart'])[2]")
    public WebElement addToCart;

}
