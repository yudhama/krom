package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage {
    
    @AndroidFindBy(accessibility = "test-Description")
    private WebElement productName;
    
    @AndroidFindBy(accessibility = "test-Price")
    private WebElement productPrice;
    
    @AndroidFindBy(accessibility = "test-ADD TO CART")
    private WebElement addToCartButton;
    
    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement backButton;
    
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc='test-Description']")
    private WebElement productDetailsContainer;
    
    public ProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }
    
    public boolean isProductDetailsPageDisplayed() {
        return isElementDisplayed(productDetailsContainer) && isElementDisplayed(addToCartButton);
    }
    
    public boolean isProductNameDisplayed() {
        return isElementDisplayed(productName);
    }
    
    public boolean isProductPriceDisplayed() {
        return isElementDisplayed(productPrice);
    }
    
    public boolean isAddToCartButtonVisible() {
        return isElementDisplayed(addToCartButton);
    }
    
    public void tapAddToCartButton() {
        waitAndClick(addToCartButton);
    }
    
    public void tapBackButton() {
        waitAndClick(backButton);
    }
    
    public String getProductName() {
        return productName.getText();
    }
    
    public String getProductPrice() {
        return productPrice.getText();
    }
}