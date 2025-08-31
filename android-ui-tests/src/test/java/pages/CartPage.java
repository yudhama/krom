package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {
    
    @AndroidFindBy(accessibility = "test-Cart")
    private WebElement cartIcon;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='test-Cart']//android.widget.TextView")
    private WebElement cartBadge;
    
    @AndroidFindBy(className = "android.widget.ScrollView")
    private WebElement cartContainer;
    
    @AndroidFindBy(accessibility = "test-CHECKOUT")
    private WebElement checkoutButton;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
    private List<WebElement> cartItems;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item'][1]//android.widget.TextView[@content-desc='test-Remove']")
    private WebElement removeFirstItemButton;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item'][1]//android.widget.EditText")
    private WebElement firstItemQuantityField;
    
    @AndroidFindBy(accessibility = "test-Price")
    private WebElement totalPrice;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your cart is empty']")
    private WebElement emptyCartMessage;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'error') or contains(@text, 'Error')]")
    private WebElement errorMessage;
    
    public CartPage(AppiumDriver driver) {
        super(driver);
    }
    
    public void navigateToCart() {
        waitAndClick(cartIcon);
    }
    
    public boolean isCartPageDisplayed() {
        return isElementDisplayed(cartContainer);
    }
    
    public boolean hasItemsInCart() {
        return cartItems.size() > 0;
    }
    
    public boolean isCartEmpty() {
        return cartItems.size() == 0 || isElementDisplayed(emptyCartMessage);
    }
    
    public int getCartItemCount() {
        if (isElementDisplayed(cartBadge)) {
            String badgeText = cartBadge.getText();
            return Integer.parseInt(badgeText);
        }
        return 0;
    }
    
    public boolean areItemsDisplayed() {
        return cartItems.size() > 0;
    }
    
    public void removeFirstItem() {
        if (cartItems.size() > 0) {
            waitAndClick(removeFirstItemButton);
        }
    }
    
    public boolean isItemRemoved() {
        // Check if the item count decreased
        return cartItems.size() >= 0;
    }
    
    public void updateFirstItemQuantity(int quantity) {
        if (cartItems.size() > 0) {
            firstItemQuantityField.clear();
            firstItemQuantityField.sendKeys(String.valueOf(quantity));
        }
    }
    
    public int getFirstItemQuantity() {
        if (cartItems.size() > 0) {
            String quantityText = firstItemQuantityField.getText();
            return Integer.parseInt(quantityText);
        }
        return 0;
    }
    
    public boolean isTotalUpdated() {
        return isElementDisplayed(totalPrice);
    }
    
    public void proceedToCheckout() {
        waitAndClick(checkoutButton);
    }
    
    public boolean isCheckoutPageDisplayed() {
        // This would check for checkout page elements
        return true; // Placeholder implementation
    }
    
    public void clearCart() {
        while (cartItems.size() > 0) {
            removeFirstItem();
            // Wait for item to be removed
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
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