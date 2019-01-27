package eu.mobile.step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import eu.mobile.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Hooks {

    //    Cucumber injects current Scenario
    @After
    public void takeScreenshotOnFail(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                EventFiringWebDriver wd = new EventFiringWebDriver(
                        DriverFactory.getDefaultDriver());
                byte[] screenshot = wd.getScreenshotAs(OutputType.BYTES);
//                include screenshot in Cucumber report
                scenario.embed(screenshot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
