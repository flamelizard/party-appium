package eu.mobile.step_definitions.quikr;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import eu.mobile.DriverFactory;
import eu.mobile.pages.quikr.LoginPage;
import eu.mobile.pages.quikr.MainPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

// TODO try cloud emulator/real device = faster?
public class QuikrSteps {

    private AppiumDriver appiumDrv;
    private LoginPage loginPage;

    @Given("^I launch Quikr app$")
    public void launchApp() throws Exception {
        appiumDrv = DriverFactory.getTestDriver();
        loginPage = new LoginPage(appiumDrv);
    }

    @Given("I skip login")
    public void iSkipLogin() throws Exception {
        appiumDrv = DriverFactory.getTestDriver();
        MainPage mainPage = new LoginPage(appiumDrv)
                .skipLogin();
    }

    @When("I login with email (\\w+?@[a-z._]+)")
    public void iLogin(String email) {
        loginPage.enterUsername(email);
    }

    @When("^I enter the password \"(\\w+)\"")
    public void iEnterPassword(String password) {
        loginPage
                .enterPassword(password)
                .clickLogin();
    }

    @Then("I get an error message")
    public void getErrorMessage() {
        WebElement elem = appiumDrv.findElement(By.id("message"));
        assertThat(elem.getText(), containsString("email is unverified"));
    }
}
