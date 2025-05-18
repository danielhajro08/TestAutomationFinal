package pageElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WomenPageElement {
 WebDriver driver;
    public WomenPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//a[@class='product-image'])[1]")
    public WebElement firstWomenElement;

    @FindBy(xpath = "(//select[@title='Sort By'])[1]")
    public WebElement selectSorting;

    @FindBy(xpath = "(//option[contains(text(),'Price')])[1]")
    public WebElement optionPrice;

    @FindBy(xpath = "(//span//span[@class='price']) | (//p//span[@class='price' and not(contains(@id, 'old-price'))])")
    public List<WebElement> pricesSorted;

    @FindBy(xpath = "(//a[contains(text(),'Add to Wishlist')])[1]")
    public WebElement firstToWishlist;

    @FindBy(xpath = "(//a[contains(text(),'Add to Wishlist')])[2]")
    public WebElement secondToWishlist;

    @FindBy(linkText = "ACCOUNT")
    public WebElement accountButton;

    @FindBy(xpath = "//a[contains(@title,'My Wishlist')]")
    public WebElement wishlistAnchor;
}
