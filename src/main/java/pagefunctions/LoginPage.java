package pagefunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitManager;

public class LoginPage extends BasePage {
	private final By loginButton = By.id("email");
	private final By passwordField = By.id("password");
	private final By loginButtonLocator = By.id("submitLoginBtn");
	private WaitManager waitManager;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.waitManager = new WaitManager(driver);
	}

	public void enterUsername(String username) {
		WebElement element = waitManager.waitForVisibility(loginButton, "Login Button");
		element.clear();
		element.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement element = waitManager.waitForVisibility(passwordField, "Password Field");
		element.clear();
		element.sendKeys(password);
	}

	public void clickLoginButton() {
		WebElement element = waitManager.waitForClickable(loginButtonLocator, "Login Button");
        element.click();
	}
}
