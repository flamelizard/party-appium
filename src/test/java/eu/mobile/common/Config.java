package eu.mobile.common;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

// Populated by Gson
public class Config {
    public String provider;
    public String server;
    public String username;
    public String accesskey;
    public Map<String, String> capabilities;

    public String getProvider() {
        return provider;
    }

    public String getServer() {
        return server;
    }

    public String getUsername() {
        return username;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public DesiredCapabilities getCapabilities() {
//        TODO expand app filename to fullpath
        return new DesiredCapabilities(capabilities);
    }
}
