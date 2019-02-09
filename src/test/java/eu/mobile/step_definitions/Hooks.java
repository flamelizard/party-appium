package eu.mobile.step_definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import eu.mobile.DriverFactory;
import eu.mobile.common.Appium;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;

public class Hooks {
    private Appium appium;

    @Before
    public void startAppium() throws Exception {
        appium = Appium.startServer();
    }

    @After
    public void stopAppium() {
        if (appium != null) {
            appium.stop();
        }
    }

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

    @After
    public void cleanUp() throws IOException {
//        quit for SauceLabs/Maven run to properly complete
        DriverFactory.destroyDefaultDriver();
    }
}
