package eu.mobile.pages.tasks;

import eu.mobile.pages.BasePage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListView extends BasePage {

    private By hamburgerMenu = MobileBy.AccessibilityId("Open");

    @FindBy(xpath = "//android.widget.CheckedTextView[@text = 'New List']")
    private WebElement newList;

    @FindBy(xpath = "//android.widget.CheckedTextView[@text = 'Settings']")
    private WebElement appSettings;

    @FindBy(id = "task_list_name")
    private WebElement listName;

    @FindBy(id = "save")
    private WebElement createList;

    private By listSettings = MobileBy.AccessibilityId("More options");

    @FindBy(id = "recycler_view")
    private WebElement addTaskHintBanner;

    @FindBy(id = "action_add_task")
    private WebElement addTaskBtn;

    @FindBy(id = "quick_add_text")
    private WebElement quickAddInput;

    @FindBy(id = "quick_add")
    private WebElement quickAdd;

    @FindBy(xpath = "//android.widget.TextView[@text = 'Delete List']")
    private WebElement deleteList;

    @FindBy(xpath = "//android.widget.Button[@text = 'YES, DELETE']")
    private WebElement confirmDeleteList;

    public ListView(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ListView createNewList(String name) {
        driver.findElement(hamburgerMenu).click();
        newList.click();
        listName.sendKeys(name);
        createList.click();
        return this;
    }

    public ListView deleteActiveList() {
        driver.findElement(listSettings).click();
        deleteList.click();
        confirmDeleteList.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(hamburgerMenu));
        return this;
    }

}
