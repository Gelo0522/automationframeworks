package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverManager;
import utils.ScreenshotUtils;

public class CucumberHooks {
    @Before
    public void setUp(Scenario scenario) {
        System.out.println(">>> Starting Scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
    	if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.takeScreenshotAsBytes(DriverManager.getDriver());
            scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
            DriverManager.quitDriver();
        }
    	System.out.println("<<< Finished Scenario: " + scenario.getName());
        DriverManager.quitDriver();
        
    }
}
