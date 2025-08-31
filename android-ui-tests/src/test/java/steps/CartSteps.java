package steps;

import io.cucumber.java.en.*;
import pages.ProductDetailsPage;
import pages.CartPage;
import utils.DriverManager;
import org.testng.Assert;

public class CartSteps {
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    
    public CartSteps() {
        this.productDetailsPage = new ProductDetailsPage(DriverManager.getDriver());
        this.cartPage = new CartPage(DriverManager.getDriver());
    }
    
    @Given("I am on the product details page")
    public void i_am_on_the_product_details_page() {
        Assert.assertTrue(productDetailsPage.isProductDetailsPageDisplayed(), "Should be on product details page");
    }
    
    @Given("I have items in my cart")
    public void i_have_items_in_my_cart() {
        // Add a product to cart first
        productDetailsPage.tapAddToCartButton();
        Assert.assertTrue(cartPage.hasItemsInCart(), "Cart should have items");
    }
    
    @Given("my cart is empty")
    public void my_cart_is_empty() {
        cartPage.clearCart();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
    }
    
    @When("I tap the add to cart button")
    public void i_tap_the_add_to_cart_button() {
        productDetailsPage.tapAddToCartButton();
    }
    
    @When("I navigate to the cart")
    public void i_navigate_to_the_cart() {
        cartPage.navigateToCart();
    }
    
    @When("I remove an item from the cart")
    public void i_remove_an_item_from_the_cart() {
        cartPage.removeFirstItem();
    }
    
    @When("I update the quantity of an item to {int}")
    public void i_update_the_quantity_of_an_item_to(int quantity) {
        cartPage.updateFirstItemQuantity(quantity);
    }
    
    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage.proceedToCheckout();
    }
    
    @When("I try to add the same product multiple times")
    public void i_try_to_add_the_same_product_multiple_times() {
        for (int i = 0; i < 5; i++) {
            productDetailsPage.tapAddToCartButton();
        }
    }
    
    @When("I try to update quantity to {int}")
    public void i_try_to_update_quantity_to(int invalidQuantity) {
        cartPage.updateFirstItemQuantity(invalidQuantity);
    }
    
    @When("I try to proceed to checkout with empty cart")
    public void i_try_to_proceed_to_checkout_with_empty_cart() {
        cartPage.proceedToCheckout();
    }
    
    @Then("the product should be added to the cart")
    public void the_product_should_be_added_to_the_cart() {
        Assert.assertTrue(cartPage.hasItemsInCart(), "Product should be added to cart");
    }
    
    @Then("the cart badge should show {int}")
    public void the_cart_badge_should_show(int expectedCount) {
        int actualCount = cartPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount, "Cart badge should show correct count");
    }
    
    @Then("I should see the cart page")
    public void i_should_see_the_cart_page() {
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Should be on cart page");
    }
    
    @Then("the cart should display the added items")
    public void the_cart_should_display_the_added_items() {
        Assert.assertTrue(cartPage.areItemsDisplayed(), "Cart items should be displayed");
    }
    
    @Then("the item should be removed from the cart")
    public void the_item_should_be_removed_from_the_cart() {
        Assert.assertTrue(cartPage.isItemRemoved(), "Item should be removed from cart");
    }
    
    @Then("the cart total should be updated")
    public void the_cart_total_should_be_updated() {
        Assert.assertTrue(cartPage.isTotalUpdated(), "Cart total should be updated");
    }
    
    @Then("the quantity should be updated to {int}")
    public void the_quantity_should_be_updated_to(int expectedQuantity) {
        int actualQuantity = cartPage.getFirstItemQuantity();
        Assert.assertEquals(actualQuantity, expectedQuantity, "Quantity should be updated correctly");
    }
    
    @Then("I should be redirected to the checkout page")
    public void i_should_be_redirected_to_the_checkout_page() {
        Assert.assertTrue(cartPage.isCheckoutPageDisplayed(), "Should be on checkout page");
    }
    
    @Then("the cart should show multiple quantities of the same product")
    public void the_cart_should_show_multiple_quantities_of_the_same_product() {
        int quantity = cartPage.getFirstItemQuantity();
        Assert.assertTrue(quantity > 1, "Cart should show multiple quantities");
    }
    
    @Then("I should see an error message about invalid quantity")
    public void i_should_see_an_error_message_about_invalid_quantity() {
        Assert.assertTrue(cartPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMessage = cartPage.getErrorMessage();
        Assert.assertTrue(errorMessage.toLowerCase().contains("quantity") || 
                         errorMessage.toLowerCase().contains("invalid"),
                         "Error should mention quantity issue");
    }
    
    @Then("I should see an error message about empty cart")
    public void i_should_see_an_error_message_about_empty_cart() {
        Assert.assertTrue(cartPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String errorMessage = cartPage.getErrorMessage();
        Assert.assertTrue(errorMessage.toLowerCase().contains("empty") || 
                         errorMessage.toLowerCase().contains("cart"),
                         "Error should mention empty cart");
    }
}