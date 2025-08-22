package stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.SpotBugsPage;
import utils.DriverManager;

public class SpotBugsDef extends BaseDef{

	public SpotBugsDef() {
		this.driver = DriverManager.getDriver(); 
		SpotBugsPage = new SpotBugsPage(driver);
	}
	@When("I fill in the registration form with the following details:")
	public void iNavigateToSpotBugsPage(DataTable table) {
		 Map<String, String> formDetails = table.asMap(String.class, String.class);
		 System.out.println("Form Details: " + formDetails);
		SpotBugsPage.fillInSpotBugsFields(formDetails);
	}
	
	
	
	@And("I click on {string} button")
	public void iClickOnButton(String buttonName) {
		// Assuming SpotBugsPage has a method to click buttons
		switch (buttonName.toLowerCase()) {
		case "register":
			SpotBugsPage.clickRegisterButton();
			break;
		case "cancel":
			// Assuming SpotBugsPage has a method to click cancel button
			break;
		default:
			throw new IllegalArgumentException("Unknown button: " + buttonName);
		}
	}
	@Then("I should see the message {string}")
	public void iShouldSeeTheMessage(String message,DataTable table) {
		assertEquals(message, SpotBugsPage.isMessageDisplayed(), "Page title should match");
		Map<String, String> formDetails = table.asMap(String.class, String.class);
		System.out.println("Form Details for Validation: " + formDetails);
		if(message.equals("Successfully registered the following information"))
			SpotBugsPage.validateInputs(formDetails);
	}


}
