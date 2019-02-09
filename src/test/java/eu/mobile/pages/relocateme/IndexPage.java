package eu.mobile.pages.relocateme;

import eu.mobile.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IndexPage extends BasePage {
    @FindBy(css = "section.hero")
    private WebElement mainSection;

    @FindBy(className = "menu-icon")
    private WebElement menuBtn;

    @FindBy(id = "gdpr-cookie-message")
    private WebElement gdprBanner;

    @FindBy(id = "gdpr-cookie-accept")
    private WebElement gdprAccept;

    @FindBy(css = ".close-button[data-toggle='mobile-menu']")
    private WebElement menuClose;

    @FindBy(id = "search-query")
    private WebElement searchQuery;

    @FindBy(id = "search-place")
    private WebElement searchPlace;

    @FindBy(className = "search-form__button")
    private WebElement searchBtn;

    public IndexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(mainSection));
        acceptGdprMessage();
    }

    public IndexPage openMenu() {
        menuBtn.click();
        return this;
    }

    public IndexPage closeMenu() {
        menuClose.click();
        return this;
    }

    public void searchFor(String query, String place) {
        searchQuery.sendKeys(query);
        searchPlace.sendKeys(place);
        searchBtn.click();
    }

    private void acceptGdprMessage() {
        gdprAccept.click();
    }


}
