package eu.mobile.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import eu.mobile.DriverFactory;
import eu.mobile.pages.tasks.ListView;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TasksSteps {
    //    TODO inject webdriver instance, driver should be provisioned in Hooks
    private WebDriver driver;
    private ListView listView;

    @Given("I launch app")
    public void iLaunchApp() throws Exception {
        driver = DriverFactory.getDefaultDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        listView = new ListView(driver);
    }

    @When("I create list")
    public void iCreateList() {
        listView.createNewList("leftovers");
    }

    @Then("I delete active list")
    public void iDeleteList() {
        listView.deleteActiveList();
    }
}
