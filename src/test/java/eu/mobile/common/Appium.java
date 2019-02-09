package eu.mobile.common;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appium {
    private AppiumDriverLocalService appium;

    private Appium() {
    }

    public static Appium startServer() {
        Appium server = new Appium();
        server.start();
        return server;
    }

    private void start() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMdd_HHmmss");
        String filename = "appium_log_" + df.format(LocalDateTime.now()) + ".txt";
        File logDir = Common.getLogsDir();
        if (!logDir.isDirectory())
            System.out.println("Warning: log folder does not exist");
        File logFile = new File(logDir, filename);

//        log level console:file
        appium = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "error:debug")
                        .withLogFile(logFile)
        );
        System.out.println("Starting Appium...");
        System.out.println("[Logging to " + logFile + "]");
        appium.start();
    }

    public void stop() {
        System.out.println("Stopping Appium ...");
        appium.stop();
    }

    public String getServerUrl() {
        return appium.getUrl().toString();
    }
}
