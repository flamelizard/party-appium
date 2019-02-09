package eu.mobile.pages.quikr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    //        System widgets require full ID including package name
    private By rootedDeviceSafetyDialog = By.id("android:id/button1");

    @FindBy(id = "skip")
    private WebElement skipLogIn;

    @FindBy(id = "login_register_view")
    private WebElement loginName;

    @FindBy(id = "login_password")
    private WebElement loginPassword;

    @FindBy(id = "continue_login")
    private WebElement continueBtn;

    @FindBy(id = "login_button")
    private WebElement loginBtn;

    @FindBy(id = "sign_in_button")
    private WebElement googleLoginBtn;

    //    first part is element class
    @FindBy(xpath = "//android.widget.TextView[@text = 'Login / Register']")
    private WebElement TopHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        WebElement safetyDialog = waitForElement(rootedDeviceSafetyDialog, 3);
        if (safetyDialog != null) safetyDialog.click();
    }

    public MainPage skipLogin() {
        skipLogIn.click();
        return new MainPage(driver);
    }

    public LoginPage enterUsername(String username) {
        loginName.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        loginPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        loginBtn.click();
    }

    public void loginByUnverifiedEmail(String email, String password) {
        loginName.sendKeys(email);
        loginPassword.sendKeys(password);
        loginBtn.click();
    }

}
