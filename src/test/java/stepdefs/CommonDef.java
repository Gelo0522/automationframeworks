package stepdefs;
import pagefunctions.CommonElements;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.DriverManager;

public class CommonDef extends BaseDef {

    private WebDriver driver;          // class-level driver
    private CommonElements CommonElements;


    @Given("User opens {string} and navigates to {string} page")
    public void userOpensBrowserAndNavigateToPage(String browser, String url) {
        this.driver = DriverManager.getDriver(browser, false);
        this.CommonElements = new CommonElements(driver); // refresh page objects if needed
        driver.get(url);
    }
    
    @When("I select {string} from the menu")
    public void iSelectFromTheMenu(String menuOption) {
        CommonElements.selectMenuOption(menuOption);
        System.out.println("success");
    }

}
