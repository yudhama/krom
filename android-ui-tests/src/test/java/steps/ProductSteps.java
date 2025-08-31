package steps;

import io.cucumber.java.en.*;
import pages.ProductsPage;
import pages.ProductDetailsPage;
import utils.DriverManager;
import org.testng.Assert;

public class ProductSteps {
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;
    
    public ProductSteps() {
        this.productsPage = new ProductsPage(DriverManager.getDriver());
        this.productDetailsPage = new ProductDetailsPage(DriverManager.getDriver());
    }
    
    @Given("I am on the products page")
    public void i_am_on_the_products_page() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Should be on products page");
    }
    
    @When("I tap on the first product")
    public void i_tap_on_the_first_product() {
        productsPage.tapFirstProduct();
    }
    
    @When("I tap on product {string}")
    public void i_tap_on_product(String productName) {
        productsPage.tapProductByName(productName);
    }
    
    @When("I apply filter {string}")
    public void i_apply_filter(String filterOption) {
        productsPage.applyFilter(filterOption);
    }
    
    @When("I sort products by {string}")
    public void i_sort_products_by(String sortOption) {
        productsPage.sortProducts(sortOption);
    }
    
    @When("I scroll down to view more products")
    public void i_scroll_down_to_view_more_products() {
        productsPage.scrollDown();
    }
    
    @Then("I should see the product details page")
    public void i_should_see_the_product_details_page() {
        Assert.assertTrue(productDetailsPage.isProductDetailsPageDisplayed(), "Should be on product details page");
    }
    
    @Then("the product name should be displayed")
    public void the_product_name_should_be_displayed() {
        Assert.assertTrue(productDetailsPage.isProductNameDisplayed(), "Product name should be visible");
    }
    
    @Then("the product price should be displayed")
    public void the_product_price_should_be_displayed() {
        Assert.assertTrue(productDetailsPage.isProductPriceDisplayed(), "Product price should be visible");
    }
    
    @Then("the add to cart button should be visible")
    public void the_add_to_cart_button_should_be_visible() {
        Assert.assertTrue(productDetailsPage.isAddToCartButtonVisible(), "Add to cart button should be visible");
    }
    
    @Then("products should be filtered by {string}")
    public void products_should_be_filtered_by(String filterType) {
        Assert.assertTrue(productsPage.areProductsFilteredBy(filterType), "Products should be filtered correctly");
    }
    
    @Then("products should be sorted by {string}")
    public void products_should_be_sorted_by(String sortType) {
        Assert.assertTrue(productsPage.areProductsSortedBy(sortType), "Products should be sorted correctly");
    }
    
    @Then("more products should be visible")
    public void more_products_should_be_visible() {
        Assert.assertTrue(productsPage.areMoreProductsVisible(), "More products should be visible after scrolling");
    }
    
    @Then("I should see an error message about invalid product")
    public void i_should_see_an_error_message_about_invalid_product() {
        Assert.assertTrue(productsPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMessage = productsPage.getErrorMessage();
        Assert.assertTrue(errorMessage.toLowerCase().contains("product") || 
                         errorMessage.toLowerCase().contains("not found"),
                         "Error should mention product issue");
    }
    
    @Then("the filter should show no results")
    public void the_filter_should_show_no_results() {
        Assert.assertTrue(productsPage.isNoResultsMessageDisplayed(), "No results message should be shown");
    }
    
    @Then("the sort option should remain unchanged")
    public void the_sort_option_should_remain_unchanged() {
        Assert.assertTrue(productsPage.isSortOptionUnchanged(), "Sort option should remain the same");
    }
}