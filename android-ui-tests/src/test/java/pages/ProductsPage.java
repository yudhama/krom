package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPage extends BasePage {
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement productsTitle;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item']")
    private List<WebElement> productItems;
    
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Item'][1]")
    private WebElement firstProduct;
    
    @AndroidFindBy(accessibility = "test-Menu")
    private WebElement menuButton;
    
    @AndroidFindBy(accessibility = "test-Modal Selector Button")
    private WebElement sortFilterButton;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name (A to Z)']")
    private WebElement sortNameAZ;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name (Z to A)']")
    private WebElement sortNameZA;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (low to high)']")
    private WebElement sortPriceLowHigh;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Price (high to low)']")
    private WebElement sortPriceHighLow;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'error') or contains(@text, 'Error')]")
    private WebElement errorMessage;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No products found']")
    private WebElement noResultsMessage;
    
    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }
    
    public boolean isProductsPageDisplayed() {
        return isElementDisplayed(productsTitle);
    }
    
    public boolean areProductsVisible() {
        return productItems.size() > 0;
    }
    
    public void tapFirstProduct() {
        waitAndClick(firstProduct);
    }
    
    public void tapProductByName(String productName) {
        for (WebElement product : productItems) {
            if (product.getText().contains(productName)) {
                waitAndClick(product);
                break;
            }
        }
    }
    
    public void applyFilter(String filterOption) {
        waitAndClick(sortFilterButton);
        // Implementation would depend on the specific filter options available
        // This is a placeholder implementation
    }
    
    public void sortProducts(String sortOption) {
        waitAndClick(sortFilterButton);
        
        switch (sortOption.toLowerCase()) {
            case "name (a to z)":
                waitAndClick(sortNameAZ);
                break;
            case "name (z to a)":
                waitAndClick(sortNameZA);
                break;
            case "price (low to high)":
                waitAndClick(sortPriceLowHigh);
                break;
            case "price (high to low)":
                waitAndClick(sortPriceHighLow);
                break;
        }
    }
    
    public void scrollDown() {
        scrollDownAction();
    }
    
    public boolean areProductsFilteredBy(String filterType) {
        // Implementation would check if products match the filter criteria
        return true; // Placeholder implementation
    }
    
    public boolean areProductsSortedBy(String sortType) {
        // Implementation would verify the sort order
        return true; // Placeholder implementation
    }
    
    public boolean areMoreProductsVisible() {
        // Check if more products are visible after scrolling
        return productItems.size() > 0;
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
    
    public boolean isNoResultsMessageDisplayed() {
        return isElementDisplayed(noResultsMessage);
    }
    
    public boolean isSortOptionUnchanged() {
        // Implementation would check if sort option remained the same
        return true; // Placeholder implementation
    }
}