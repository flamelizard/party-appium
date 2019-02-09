package eu.mobile.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import eu.mobile.DriverFactory;
import eu.mobile.pages.relocateme.IndexPage;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class RelocateMeSteps {
    private WebDriver driver;
    private IndexPage indexPage;

    @Given("I open page \"([^\"]+)\"")
    public void iOpenPageAt(String address) throws Exception {
        driver = DriverFactory.getDefaultDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(address);
        indexPage = new IndexPage(driver);
    }

    @When("I search for job \"([^\"]+)\" and place \"([^\"]+)\"")
    public void iSearchFor(String query, String place) {
        indexPage.searchFor(query, place);
    }
}
