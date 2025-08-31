Feature: Shopping Cart Functionality
  As a user with items in cart
  I want to manage my cart items
  So that I can proceed to checkout

  Background:
    Given the demo app is launched
    And I am logged in with valid credentials
    And I have added products to my cart

  @positive @cart
  Scenario: View cart contents
    When I tap the cart icon
    Then I should see the cart page
    And I should see all added products
    And I should see the total price
    And I should see "Checkout" button

  @positive @cart
  Scenario: Remove item from cart
    Given I am on the cart page
    When I tap "Remove" button for the first item
    Then the item should be removed from cart
    And the cart count should decrease by 1
    And the total price should be updated

  @negative @cart
  Scenario: Checkout with empty cart
    Given I have removed all items from cart
    When I tap the "Checkout" button
    Then I should see an error message "Your cart is empty"
    And I should remain on the cart page

  @negative @cart
  Scenario: Access cart without login
    Given I am logged out
    When I try to access the cart directly
    Then I should be redirected to login page
    And I should see a message "Please login to access cart"

  @negative @cart
  Scenario: Cart persistence after app restart
    Given I have items in cart
    When I close and reopen the app
    And I login again
    Then my cart should be empty
    And I should see "Your cart is empty" message