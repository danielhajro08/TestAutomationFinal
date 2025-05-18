package pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishlistPageElement {
WebDriver driver;
    public WishlistPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "success-msg")
    public WebElement success;

    @FindBy(xpath = "(//button[@title='Add to Cart'])[2]")
    public WebElement addFirstToCart;
    
}
