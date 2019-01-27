package eu.mobile.pages.tasks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Base(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }
}
