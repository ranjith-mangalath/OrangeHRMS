import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartFromPLP {
	
	private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By removeButton = By.id("remove-sauce-labs-backpack");
	
    public void addToCart(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
    }
    
    public boolean isRemoveButtonDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton)).isDisplayed();
    }
    
    public void removeFromCart(WebDriver driver) {
        if (isRemoveButtonDisplayed(driver)) {
            driver.findElement(removeButton).click();
        }
    }
    public boolean isAddToCartButtonDisplayed(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
