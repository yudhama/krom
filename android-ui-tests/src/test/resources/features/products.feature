Feature: Products Page Functionality
  As a logged-in user
  I want to browse and interact with products
  So that I can add items to my cart

  Background:
    Given the demo app is launched
    And I am logged in with valid credentials
    And I am on the products page

  @positive @products
  Scenario: View product details
    When I tap on the first product
    Then I should see the product details page
    And I should see product name, description, and price
    And I should see "Add to Cart" button

  @positive @products
  Scenario: Add product to cart
    When I tap "Add to Cart" button for the first product
    Then the button text should change to "Remove"
    And the cart badge should show "1"
    And the product should be added to cart

  @positive @products
  Scenario: Sort products by price low to high
    When I tap the sort dropdown
    And I select "Price (low to high)"
    Then products should be sorted by price in ascending order
    And the first product should have the lowest price

  @negative @products
  Scenario: Navigate back from product details without network
    Given the device has no network connection
    When I tap on the first product
    And I tap the back button
    Then I should return to the products page
    And all products should still be visible

  @negative @products
  Scenario: Add product to cart when cart is full
    Given I have added 10 products to cart
    When I try to add another product to cart
    Then I should see a warning message about cart limit
    And the product should not be added to cart