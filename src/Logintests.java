import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.JsonNode;



public class Logintests {
    
    // **Helper method to perform login**
    public void performLogin(WebDriver driver, String username, String password) {
        WebElement loginBox = driver.findElement(By.name("user-name"));
        WebElement passwordBox = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.name("login-button"));

        loginBox.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE); // Clear the login field
        passwordBox.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE); // Clear the password field
        loginBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();
    }

    // **Method to test invalid credentials**
    public void testInvalidCredentials(WebDriver driver, JsonNode invalidCredentials) {
        for (JsonNode credential : invalidCredentials) {
            String username = credential.get("username").asText();
            String password = credential.get("password").asText();

            System.out.println("Testing Invalid Credentials - Username: " + username);

            performLogin(driver, username, password);

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.saucedemo.com/inventory.html";

            if (actualUrl.equals(expectedUrl)) {
                System.out.println("Unexpected: Invalid Login was successful!");
            } else {
                System.out.println("Login Failed as expected for invalid credentials.");
            }
        }
    }

    // **Method to test valid credentials**
    public void testValidCredentials(WebDriver driver, JsonNode validCredentials) {
        for (JsonNode credential : validCredentials) {
            String username = credential.get("username").asText();
            String password = credential.get("password").asText();

            System.out.println("Testing Valid Credentials - Username: " + username);
            // Print credentials before passing them to login
            System.out.println("Testing Valid Credentials:");
            System.out.println("   Username: " + username);
            System.out.println("   Password: " + password);

            performLogin(driver, username, password);

            // Wait for the page to load before checking URL
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("inventory.html"));

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://www.saucedemo.com/inventory.html";

            if (actualUrl.equals(expectedUrl)) {
                System.out.println("Login Successful for valid credentials.");
            } else {
                System.out.println("Unexpected: Login failed for valid credentials!");
            }
        }
    }
}

