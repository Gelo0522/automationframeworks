package pagefunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.jooq.lambda.Seq;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ScrollUtils;
import utils.WaitManager;

public class SpotBugsPage extends BasePage {
	private WaitManager waitManager;
	ScrollUtils scrollUtils;
	private final By registrationForm = By.id("registerForm");
	private final By firstName = By.id("firstName");
	private final By lastName = By.id("lastName");
	private final By phone = By.id("phone");
	private final By country = By.cssSelector("#countries_dropdown_menu > option");
	private final By emailAddress = By.id("emailAddress");
	private final By password = By.id("password");
	private final By termsAndConditionsCheckBox = By.id("exampleCheck1");
	private final By registerButton = By.id("registerBtn");
	private final By message = By.id("message");

	public SpotBugsPage(WebDriver driver) {
		super(driver);
		this.waitManager = new WaitManager(driver);
		scrollUtils = new ScrollUtils(driver);

	}

	public void clickRegisterButton() {
		WebElement element = waitManager.waitForClickable(registerButton, "Register Button");
		element.click();
	}

	public String isMessageDisplayed() {
		try {
			WebElement messageElement = waitManager.waitForVisibility(message, "Message Element");
			return messageElement.getText();
		} catch (Exception e) {
			return "No message displayed";
		}
	}

	public void fillInSpotBugsFields(Map<String, String> formDetails) {
		waitManager.waitForVisibility(registrationForm, "Registration Form");
		if (formDetails.get("FirstName") != null && !formDetails.get("FirstName").isEmpty()) {
			enterFirstName(formDetails.get("FirstName"));
		}
		if (formDetails.get("LastName") != null && !formDetails.get("LastName").isEmpty()) {
			enterLastName(formDetails.get("LastName"));
		}
		if (formDetails.get("PhoneNumber") != null && !formDetails.get("PhoneNumber").isEmpty()) {
			enterPhone(formDetails.get("PhoneNumber"));
		}
		if (formDetails.get("Country") != null && !formDetails.get("Country").isEmpty()) {
			enterCountry(formDetails.get("Country"));
		}
		if (formDetails.get("EmailAddress") != null && !formDetails.get("EmailAddress").isEmpty()) {
			enterEmailAddress(formDetails.get("EmailAddress"));
		}
		if (formDetails.get("Password") != null && !formDetails.get("Password").isEmpty()) {
			enterPassword(formDetails.get("Password"));
		}
		if (formDetails.get("TermsAndConditions") != null && !formDetails.get("TermsAndConditions").isEmpty()) {
			setTermsAndConditions(Boolean.parseBoolean(formDetails.get("TermsAndConditions")));
		}

	}

	public void enterFirstName(String firstNameValue) {
		WebElement element = waitManager.waitForVisibility(firstName, "First Name");
		element.sendKeys(firstNameValue);
	}

	public void enterLastName(String lastNameValue) {
		WebElement element = waitManager.waitForVisibility(lastName, "Last Name");
		element.sendKeys(lastNameValue);
	}

	public void enterPhone(String phoneValue) {
		WebElement element = waitManager.waitForVisibility(phone, "Phone");
		element.sendKeys(phoneValue);
	}

	public void enterCountry(String countryValue) {
		Seq.seq(waitManager.waitForAllPresence(country, "Countries"))
				.filter(option -> option.getText().trim().equalsIgnoreCase(countryValue)).findFirst()
				.orElseThrow(() -> new RuntimeException("Country '" + countryValue + "' not found")).click();
	}

	public void enterEmailAddress(String emailValue) {
		WebElement element = waitManager.waitForVisibility(emailAddress, "Email Address");
		element.sendKeys(emailValue);
	}

	public void enterPassword(String passwordValue) {
		WebElement element = waitManager.waitForVisibility(password, "Password");
		element.sendKeys(passwordValue);
	}

	public void setTermsAndConditions(boolean accept) {
		WebElement termsCheckBox = waitManager.waitForClickable(termsAndConditionsCheckBox,
				"Terms and Conditions Checkbox");

		boolean initialState = termsCheckBox.isSelected();

		if (accept && !initialState) {
			termsCheckBox.click();
			if (!termsCheckBox.isSelected()) {
				throw new RuntimeException("Failed to check the Terms and Conditions checkbox.");
			}
		} else if (!accept && initialState) {
			termsCheckBox.click();
			if (termsCheckBox.isSelected()) {
				throw new RuntimeException("Failed to uncheck the Terms and Conditions checkbox.");
			}
		}
	}

	public void validateInputs(Map<String, String> formDetails) {
		assertEquals("First Name:" + (formDetails.get("FirstName")!=null? " "+formDetails.get("FirstName") : ""),
				scrollUtils.scrollIntoView(waitManager.waitForVisibility(By.id("resultFn"), "First Name")).getText(), "First Name should match");
		assertEquals("Last Name:" + (formDetails.get("LastName")!=null? " "+formDetails.get("LastName") : ""),
				scrollUtils.scrollIntoView(waitManager.waitForVisibility(By.id("resultLn"), "Last Name")).getText(), "Last Name should match");
		assertEquals("Phone Number:" + (formDetails.get("PhoneNumber")!= null ? " " + formDetails.get("PhoneNumber") : ""),
				scrollUtils.scrollIntoView(waitManager.waitForVisibility(By.id("resultPhone"), "Phone")).getText(), "Phone should match");
		if (formDetails.get("Country") != null && !formDetails.get("Country").isEmpty()) {
			assertEquals("Country:" + formDetails.get("Country"),
					scrollUtils.scrollIntoView(waitManager.waitForVisibility(By.id("resultCountry"), "Country")).getText(), "Country should match");
		} else {
			assertEquals("Country: Select a country...",
					scrollUtils.scrollIntoView(waitManager.waitForVisibility(By.id("resultCountry"), "Country")).getText(), "Country should match");
		}
		assertEquals("Email:" + (formDetails.get("EmailAddress")!= null ? " " + formDetails.get("EmailAddress") : ""),
				scrollUtils.scrollIntoView(waitManager.waitForVisibility(By.id("resultEmail"), "Email Address")).getText(), "Email Address should match");
	}

}
