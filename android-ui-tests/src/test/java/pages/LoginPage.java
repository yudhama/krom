package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    
    @AndroidFindBy(accessibility = "test-Username")
    private WebElement usernameField;
    
    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordField;
    
    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message']//android.widget.TextView")
    private WebElement errorMessage;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN']")
    private WebElement loginTitle;
    
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }
    
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(loginTitle) && isElementDisplayed(usernameField);
    }
    
    public void enterUsername(String username) {
        waitAndSendKeys(usernameField, username);
    }
    
    public void enterPassword(String password) {
        waitAndSendKeys(passwordField, password);
    }
    
    public void tapLoginButton() {
        waitAndClick(loginButton);
    }
    
    public void clearUsername() {
        usernameField.clear();
    }
    
    public void clearPassword() {
        passwordField.clear();
    }
    
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
    
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return errorMessage.getText();
        }
        return "";
    }
}