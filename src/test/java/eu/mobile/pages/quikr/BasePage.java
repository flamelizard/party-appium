package eu.mobile.pages.quikr;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(By by, int sec) {
        wait = new WebDriverWait(driver, sec);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException ex) {
        }
        return null;
    }
}
