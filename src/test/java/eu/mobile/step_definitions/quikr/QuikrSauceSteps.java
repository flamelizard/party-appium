package eu.mobile.step_definitions.quikr;

import cucumber.api.java.en.Given;
import eu.mobile.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
KNOW HOW
AppiumDriver when running against Appium server
AndroidDriver, WebDriver against SauceLabs
 */
public class QuikrSauceSteps {
    private String SAUCE_ACCESSKEY = "12fe3ea7-5711-4ca9-a58b-7947ed8df6a2";
    private String SAUCE_USERNAME = "kurt-hux";
    private String SAUCE_APPNAME = "quikr_v10.18.apk";
    //    AndroidDriver works ok too
    private WebDriver driver;

    //    https://wiki.saucelabs.com/display/DOCS/Platform+Configurator
    public static DesiredCapabilities getSauceCaps() {
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("appiumVersion", "1.9.1");
        caps.setCapability("deviceName", "Samsung Galaxy Nexus Emulator");
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion", "4.4");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", "sauce-storage:bob123");
        return caps;
    }

    @Given("SauceLabs - I launch Quikr app")
    public void launchAppOnSauce() throws Exception {
        String sauceUrl = String.format(
                "http://%s:%s@ondemand.saucelabs.com:80/wd/hub",
                SAUCE_USERNAME, SAUCE_ACCESSKEY
        );
        DesiredCapabilities caps = getSauceCaps();
        caps.setCapability("app", "sauce-storage:" + SAUCE_APPNAME);
        driver = DriverFactory.createAndroidDriver(
                new URL(sauceUrl), caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Given("SauceLabs - I log in")
    public void iLogIn() {
        By rootedDeviceSafetyDialog = By.id("android:id/button1");
        driver.findElement(rootedDeviceSafetyDialog).click();
//        driver.findElement(By.id("login_register_view")).sendKeys("bob@gmail.com");
        driver.findElement(By.id("skip")).click();
        driver.findElement(By.id("searchbar_search_icon"));
        driver.quit();
    }
}
