package pageElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPageElement {
WebDriver driver;
    public CartPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "ACCOUNT")
    public WebElement accountButton;

    @FindBy(xpath = "//a[contains(text(),'Wishlist')]")
    public WebElement wishlist;

    @FindBy(xpath = "(//input[@title='Qty'])[1]")
    public WebElement quantityInput;

    @FindBy(xpath = "(//button[@class='button btn-update'])[1]")
    public WebElement updateButton;

    @FindBy(css = ".product-cart-total > .cart-price > .price")
    public List<WebElement> subtotalPriceList;

    @FindBy(css = "strong>span.price")
    public WebElement grandTotal;

    @FindBy(css = "#shopping-cart-table >tbody>tr")
    public List<WebElement> productList;

    @FindBy(xpath = "(//a[@title='Remove Item'])[2]")
    public WebElement deleteButton;

    @FindBy(xpath = "(//div[contains(@class,'cart-empty')]/p)[1]")
    public WebElement cartEmpty;

}   
