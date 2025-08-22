package stepdefs;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pagefunctions.LoginPage;
import utils.DriverManager;

public class LoginDef extends BaseDef{
    
    public LoginDef() {
        driver = DriverManager.getDriver(); 
        LoginPage = new LoginPage(driver); 
    }
	
	@When("I login as {string} with password {string}")
	public void iEnterUsername(String username, String password) {
		LoginPage.enterUsername(username);
		LoginPage.enterPassword(password);
		LoginPage.clickLoginButton();
	}
}
