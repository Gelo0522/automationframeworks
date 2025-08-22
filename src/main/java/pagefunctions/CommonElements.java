package pagefunctions;

import java.util.List;

import org.jooq.lambda.Seq;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitManager;

public class CommonElements extends BasePage{
	
	private final By menu = By.cssSelector("#ul-menu-items");
	
	
	
	WaitManager waitManager;
	public CommonElements(WebDriver driver) {
        super(driver); 
        this.waitManager = new WaitManager(driver);
    }

	 public void selectMenuOption(String menuOption) {

	        waitManager.waitForVisibility(menu, "Menu");

	        WebElement selectedMenu = Seq.seq(driver.findElement(menu).findElements(By.tagName("li")))
	                .filter(m -> m.getText().trim().equalsIgnoreCase(menuOption))
	                .findFirst()
	                .orElseThrow(() ->
	                        new RuntimeException("Menu option '" + menuOption + "' not found"));

	        // Click the selected menu
	        selectedMenu.click();
	        System.out.println("Clicked menu: " + menuOption);
	    }
	
}
