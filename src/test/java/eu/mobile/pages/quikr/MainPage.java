package eu.mobile.pages.quikr;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    @FindBy(id = "searchbar_search_icon")
    private WebElement searchIcon;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open Drawer\"]")
    private WebElement hamburgerMenu;

    @FindBy(id = "citySpinner")
    private WebElement searchCity;

    @FindBy(id = "search_ET")
    private WebElement searchCityField;

    @FindBy(id = "city_name")
    private WebElement searchCityResult;

    public MainPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.visibilityOf(searchIcon));
    }
}
