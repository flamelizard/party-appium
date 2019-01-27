package eu.mobile.common;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private String CONFIG_FILE = "config.properties";
    private String CAPABILITIES = "capabilities.json";
    private File environRoot;
    private Properties config = new Properties();

    public ConfigManager(File environRoot) throws IOException {
        this.environRoot = environRoot;
        config.load(new FileInputStream(new File(environRoot, CONFIG_FILE)));
    }

    public static String getFeaturesDir() {
        File features = new File(
                System.getProperty("user.dir"), "src/test/eu/mobile/features");
        return features.getAbsolutePath();
    }

    public DesiredCapabilities getCapabilities() throws IOException {
        File capsFile = new File(environRoot, CAPABILITIES);
        DesiredCapabilities caps = new DesiredCapabilities(Utils.fromJson(capsFile));
        switch (getDriverProvider()) {
            case "appium":
                String mobileAppPath = new File(
                        Utils.getMobileAppDir(), getAppName()).getAbsolutePath();
                caps.setCapability("app", mobileAppPath);
                break;
            case "saucelabs":
                caps.setCapability("app", getAppName());
                break;
            default:
                System.out.println("Error: Cannot set capability key <app>");
        }
//        System.out.println("[caps " + caps);
        return caps;
    }

    public Properties getConfig() {
        return config;
    }

    public String getDriverProvider() {
        return getConfig().getProperty("driver.provider").toLowerCase();
    }

    public String getAppName() {
        return getConfig().getProperty("app.name");
    }
}
