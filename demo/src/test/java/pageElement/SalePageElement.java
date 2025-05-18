package pageElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalePageElement {
    WebDriver driver;
    public SalePageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='item last']")
    public List<WebElement> saleItemList;

    @FindBy(xpath = "//p[@class='old-price']//span[@class='price']")
    public List<WebElement> old_price;

    @FindBy(xpath = "//p[@class='special-price']//span[@class='price']")
    public List<WebElement> special_price;
   
}
