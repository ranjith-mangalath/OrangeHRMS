import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListingPage {

    // Locator for the first product's name
    private By firstProductName = By.cssSelector(".inventory_item_name[data-test='inventory-item-name']");
    
    // Locator for the first product's price
    private By firstProductPrice = By.cssSelector(".inventory_item_price[data-test='inventory-item-price']");
    
    // Method to get the first product's name
    public String getFirstProductName(WebDriver driver) {
        WebElement productName = driver.findElement(firstProductName);
        return productName.getText();
    }
 // Method to get the first product's price
    public String getFirstProductPrice(WebDriver driver) {
        WebElement productPrice = driver.findElement(firstProductPrice);
        return productPrice.getText();
    }

 // Method to verify if the first product name is correct
    public boolean verifyFirstProductName(WebDriver driver) {
        String expectedProductName = "Sauce Labs Backpack";
        String actualProductName = getFirstProductName(driver);

        System.out.println("Product Name: " + actualProductName);

        return actualProductName.equals(expectedProductName);
    }

    // Method to verify if the first product price is correct
    public boolean verifyFirstProductPrice(WebDriver driver) {
        String expectedProductPrice = "$29.99";
        String actualProductPrice = getFirstProductPrice(driver);

        System.out.println("Product Price: " + actualProductPrice);

        return actualProductPrice.equals(expectedProductPrice);
    }
}