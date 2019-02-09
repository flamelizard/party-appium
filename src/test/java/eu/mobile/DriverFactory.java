package eu.mobile;

import eu.mobile.common.Config;
import eu.mobile.common.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class DriverFactory {
    //    TODO support for parallel testing, try out
    private static ThreadLocal<WebDriver> driverStorage = new ThreadLocal<>();
    private static ConfigManager configMgr;
    private static String SAUCE_URL_TEMPLATE =
            "http://%s:%s@ondemand.saucelabs.com:80/wd/hub";

    //    TODO create driver in Hook, inject to stepdef
    public static WebDriver getDefaultDriver() throws IOException {
        WebDriver driver = driverStorage.get();
        if (driver == null) {
            driver = createSessionDefaultEnv();
            driverStorage.set(driver);
        }
        return driver;
    }

    public static void destroyDefaultDriver() {
        WebDriver driver = driverStorage.get();
        if (driver == null) return;
        driver.quit();
        driverStorage.remove();
    }

    private static WebDriver createSessionDefaultEnv() throws IOException {
        configMgr = new ConfigManager();
        Config config = configMgr.getConfig();

        URL url;
        switch (config.getProvider()) {
            case "appium":
                url = new URL(config.getServer());
                return new AppiumDriver(url, config.getCapabilities());
            case "saucelabs":
                String sauceUrl = String.format("http://%s:%s@%s",
                        config.getUsername(), config.getAccesskey(),
                        config.getServer()
                );
                return new AndroidDriver(
                        new URL(sauceUrl), config.getCapabilities());
            case "browserstack":
                String bsUrl = String.format(
                        "https://%s:%s@%s",
                        config.getUsername(), config.getAccesskey(),
                        config.getServer()
                );
                return new RemoteWebDriver(new URL(bsUrl), config.getCapabilities());
            default:
                System.out.println("Error: Unsupported driver provider");
                return null;
        }
    }
}
