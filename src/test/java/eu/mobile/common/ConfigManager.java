package eu.mobile.common;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static eu.mobile.common.Common.getResourcesDir;

public class ConfigManager {
    private static String SYS_PROP_CONFIG = "TEST_CONFIG_FILE";
    private Config config;

    public ConfigManager(String filename) throws IOException {
        loadConfig(filename);
    }

    public ConfigManager() throws IOException {
        String cfgFile = getTestConfigFile();
        if (cfgFile == null || cfgFile.equals(""))
            throw new FileNotFoundException("Error: config file not set");
        loadConfig(cfgFile);
    }

    public static String getTestConfigFile() {
        return System.getProperty(SYS_PROP_CONFIG);
    }

    public static void setTestConfigFile(String filename) {
        System.setProperty(SYS_PROP_CONFIG, filename);
    }

    public static void overrideCucumberOptions(String cliOptions) {
        System.setProperty("cucumber.options", cliOptions);
    }

    private void loadConfig(String filename) throws IOException {
        File filepath = new File(getConfigDir(), filename);
        List<String> lines = Files.readAllLines(filepath.toPath());
        Gson gson = new Gson();
        config = gson.fromJson(String.join("", lines), Config.class);
    }

    public Config getConfig() {
        return config;
    }

    private File getConfigDir() {
        return new File(getResourcesDir(), "config");
    }
}
