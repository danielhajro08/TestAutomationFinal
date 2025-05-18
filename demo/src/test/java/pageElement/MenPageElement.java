package pageElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenPageElement {
 WebDriver driver;
    public MenPageElement(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@title='Black']")
    public WebElement blackColorSelect;

    @FindBy(xpath = "//li[@data-option-label='black']//a[@name='black']")
    public List<WebElement> blackColorCheckList;

    @FindBy(xpath = "//a//span[@class='price']")
    public WebElement priceValueClick;

    @FindBy(xpath = "//li[@class='item last']")
    public List<WebElement> elementsAfterClick;

    @FindBy(xpath = "//span[@class='price']")
    public List<WebElement> prices;

}
