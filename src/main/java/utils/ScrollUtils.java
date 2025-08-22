package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollUtils {

    private WebDriver driver;

    public ScrollUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Scroll until the element is in view
    public WebElement scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

}
