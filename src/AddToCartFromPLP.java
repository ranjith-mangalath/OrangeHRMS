package com.tests;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//@author - Ranjith Mangalath
public class AddToCartFromPLP {
	
	private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By removeButton = By.id("remove-sauce-labs-backpack");
	
  //@author - Ranjith Mangalath
    public void addToCart(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
    }
    
  //@author - Ranjith Mangalath
    public boolean isRemoveButtonDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton)).isDisplayed();
    }
  //@author - Ranjith Mangalath
    public void removeFromCart(WebDriver driver) {
        if (isRemoveButtonDisplayed(driver)) {
            driver.findElement(removeButton).click();
        }
    }
  //@author - Ranjith Mangalath
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
