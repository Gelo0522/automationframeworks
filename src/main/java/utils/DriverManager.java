package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    // ThreadLocal allows parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private DriverManager() {}
    
    // Initialize driver if not already created
    public static WebDriver getDriver(String browserType, boolean isIncognito) {
        if (driver.get() == null) {
            driver.set(BrowserFactory.createDriver(browserType, isIncognito));
        }
        return driver.get();
    }

    // Quit driver after scenario
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

	public static WebDriver getDriver() {
		return driver.get();
	}
}