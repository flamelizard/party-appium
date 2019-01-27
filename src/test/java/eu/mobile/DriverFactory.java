package eu.mobile;

import eu.mobile.common.ConfigManager;
import eu.mobile.common.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static String DEFAULT_ENV = "/config/env_appium";
    //    TODO support for parallel testing, try out
    private static ThreadLocal<WebDriver> driverStorage = new ThreadLocal<>();
    private static ConfigManager configMgmt;
    private static AppiumDriver appiumDriver;
    private static String SAUCE_URL_TEMPLATE =
            "http://%s:%s@ondemand.saucelabs.com:80/wd/hub";

    public static WebDriver getDefaultDriver() throws IOException {
        WebDriver driver = driverStorage.get();
        if (driver == null) {
            driver = createSessionDefaultEnv();
            driverStorage.set(driver);
        }
        return driver;
    }

    private static WebDriver createSessionDefaultEnv() throws IOException {
        File envRoot = new File(Utils.getResourcesDir(), DEFAULT_ENV);
        configMgmt = new ConfigManager(envRoot);
        Properties config = configMgmt.getConfig();

        URL url;
//  TODO fail on missing props
        switch (configMgmt.getDriverProvider()) {
            case "appium":
                url = new URL(config.getProperty("appium.server"));
                return new AppiumDriver(url, configMgmt.getCapabilities());
            case "saucelabs":
                String sauceUrl = String.format(SAUCE_URL_TEMPLATE,
                        config.getProperty("saucelabs.username"),
                        config.getProperty("saucelabs.accesskey")
                );
                return new AndroidDriver(
                        new URL(sauceUrl), configMgmt.getCapabilities());
            default:
                System.out.println("Error: Unsupported driver provider");
                return null;
        }
    }

    public static WebDriver createSessionSauceLabs(
            URL sauceUrl, DesiredCapabilities caps) {
        return new AndroidDriver(sauceUrl, caps);
    }

    public static WebDriver createAndroidDriver(URL server, DesiredCapabilities caps) {
        return new AndroidDriver(server, caps);
    }

    public static AppiumDriver getTestDriver() throws Exception {
        if (appiumDriver != null)
            return appiumDriver;
//        appiumDriver = createSession(
//                new URL(APPIUM_SERVER), getAndroidTestCaps());
//        emulator has long response time on commands
        appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return appiumDriver;
    }

    public static DesiredCapabilities getAndroidTestCaps() {
//        String mobileApp = "quikr_v10.18.apk";
        String mobileApp = "tasks.apk";
        String mobileAppPath = new File(Utils.getMobileAppDir(), mobileApp).getAbsolutePath();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "5.1");
        caps.setCapability("deviceName", "Nexus6");
        caps.setCapability("app", mobileAppPath);
        return caps;
    }

}
