package pageMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageElement.CartPageElement;

public class CartPage {
    WebDriver driver;
    CartPageElement cartPageElement;
    WebDriverWait wait;
    JavascriptExecutor js;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        cartPageElement = new CartPageElement(driver);
    }

    public void clickAccountButton() {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cartPageElement.accountButton);
        cartPageElement.accountButton.click();
    }

    public WishlistPage clickWishlist() {
        wait.until(ExpectedConditions.visibilityOf(cartPageElement.wishlist)).click();
        return new WishlistPage(driver);
    }

    public void updateQuantity() {
        wait.until(ExpectedConditions.visibilityOf(cartPageElement.quantityInput));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cartPageElement.quantityInput);
        cartPageElement.quantityInput.click();
        cartPageElement.quantityInput.clear();
        cartPageElement.quantityInput.sendKeys("2");

        wait.until(ExpectedConditions.visibilityOf(cartPageElement.updateButton));
        cartPageElement.updateButton.click();
    }

    public Double checkPriceOfElements() {
        List<Double> priceFinalList = new ArrayList<>();
        Double sum = 0.00;

        for (WebElement element : cartPageElement.subtotalPriceList) {
            try {
                String price = element.getText().replace("$", "");
                Double priceFormatted = Double.parseDouble(price);
                priceFinalList.add(priceFormatted);
            } catch (Exception e) {
                Assert.fail("Failed to parse price: " + e);
            }
        }

        for (Double el : priceFinalList) {
            try {
                sum += el;
            } catch (Exception e) {
                Assert.fail("Failed to sum price: " + e);
            }
        }
        System.out.println("list sum : " + sum);
        return sum;
    }

    public Double checkGrandSum() {
        wait.until(ExpectedConditions.visibilityOf(cartPageElement.grandTotal));
        Double grandSum = Double.parseDouble(cartPageElement.grandTotal.getText().replace("$", ""));
        System.out.println("Grand sum: " + grandSum);
        return grandSum;
    }

    public void checkCosts() {
        Assert.assertTrue(checkGrandSum().equals(checkPriceOfElements()), "Sums aint matching");
    }

    public int getCartLength(){
        wait.until(ExpectedConditions.visibilityOfAllElements(cartPageElement.productList));
        int size =cartPageElement.productList.size();
        return size;
    }

    public void deleteFirstAndCheck() {
    int attempts = 0;
    while (attempts < 2) {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartPageElement.deleteButton));
            int before = getCartLength();
            System.out.println("before: " + before);

            cartPageElement.deleteButton.click();

            List<WebElement> products = driver.findElements(By.cssSelector("#shopping-cart-table >tbody>tr"));
            wait.until(ExpectedConditions.visibilityOfAllElements(products));
            int after = products.size();
            System.out.println("after: " + after);

            Assert.assertEquals(before, after + 1, "Not Same");
            break;
        } catch (StaleElementReferenceException e) {
            attempts++;
            if (attempts == 2) {
                Assert.fail("StaleElementReferenceException in deleteFirstAndCheck()");
            }
        }
    }
}


    public void deleteItem(){
        int length = getCartLength();
        for(int i=0 ; i<length ; i++){
            cartPageElement.deleteButton.click();
        }
    }

    public void checkEmptyMessage(){
        wait.until(ExpectedConditions.visibilityOf(cartPageElement.cartEmpty));
        String msg = cartPageElement.cartEmpty.getText();
        String expmsg = "You have no items in your shopping cart.";
        Assert.assertEquals(msg, expmsg,"Not same");
    }

}
