package eu.mobile.step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import eu.mobile.DriverFactory;
import eu.mobile.pages.quikr.LoginPage;
import eu.mobile.pages.quikr.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class QuikrSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("^I launch Quikr app$")
    public void launchApp() throws Exception {
        driver = DriverFactory.getDefaultDriver();
        loginPage = new LoginPage(driver);
    }

    @Given("I skip login")
    public void iSkipLogin() throws Exception {
        driver = DriverFactory.getDefaultDriver();
        MainPage mainPage = new LoginPage(driver)
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
        WebElement elem = driver.findElement(By.id("message"));
        assertThat(elem.getText(), containsString("email is unverified"));
    }
}
