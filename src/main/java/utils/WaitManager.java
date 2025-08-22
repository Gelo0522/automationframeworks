package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager {

    private WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;
    public WaitManager(WebDriver driver, int timeoutSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    // Overloaded constructor with default timeout
    public WaitManager(WebDriver driver) {
        this(driver, DEFAULT_TIMEOUT); // calls the other constructor
    }

    // Wait for element visibility
    public WebElement waitForVisibility(By locator, String elementName) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for visibility of: " + elementName, e);
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for visibility of: " + elementName, e);
        }
    }

    // Wait for element to be clickable
    public WebElement waitForClickable(By locator, String elementName) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for clickable: " + elementName, e);
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for clickable: " + elementName, e);
        }
    }

    // Wait for presence in DOM
    public WebElement waitForPresence(By locator, String elementName) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for presence of: " + elementName, e);
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for presence of: " + elementName, e);
        }
    }
    
    public List<WebElement> waitForAllPresence(By locator, String elementName) {
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for all presence of: " + elementName, e);
        } catch (Exception e) {
            throw new RuntimeException("Error waiting for all presence of: " + elementName, e);
        }
    }
}