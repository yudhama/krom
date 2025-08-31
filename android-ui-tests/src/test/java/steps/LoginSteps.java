package steps;

import io.cucumber.java.en.*;
import pages.LoginPage;
import pages.ProductsPage;
import utils.DriverManager;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage;
    private ProductsPage productsPage;
    
    public LoginSteps() {
        this.loginPage = new LoginPage(DriverManager.getDriver());
        this.productsPage = new ProductsPage(DriverManager.getDriver());
    }
    
    @Given("the demo app is launched")
    public void the_demo_app_is_launched() {
        DriverManager.initializeDriver();
        // App should launch automatically with Appium
    }
    
    @Given("I am on the login screen")
    public void i_am_on_the_login_screen() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
    }
    
    @When("I enter username {string}")
    public void i_enter_username(String username) {
        loginPage.enterUsername(username);
    }
    
    @When("I enter password {string}")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }
    
    @When("I tap the login button")
    public void i_tap_the_login_button() {
        loginPage.tapLoginButton();
    }
    
    @When("I leave username field empty")
    public void i_leave_username_field_empty() {
        loginPage.clearUsername();
    }
    
    @When("I leave password field empty")
    public void i_leave_password_field_empty() {
        loginPage.clearPassword();
    }
    
    @Then("I should be redirected to the products page")
    public void i_should_be_redirected_to_the_products_page() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Should be on products page");
    }
    
    @Then("I should see the products list")
    public void i_should_see_the_products_list() {
        Assert.assertTrue(productsPage.areProductsVisible(), "Products should be visible");
    }
    
    @Then("the login button should be enabled")
    public void the_login_button_should_be_enabled() {
        Assert.assertTrue(loginPage.isLoginButtonEnabled(), "Login button should be enabled");
    }
    
    @Then("no error messages should be displayed")
    public void no_error_messages_should_be_displayed() {
        Assert.assertFalse(loginPage.isErrorMessageDisplayed(), "No error message should be shown");
    }
    
    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            "Expected error message: " + expectedMessage + ", but got: " + actualMessage);
    }
    
    @Then("I should remain on the login screen")
    public void i_should_remain_on_the_login_screen() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Should remain on login page");
    }
}