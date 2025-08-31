Feature: Store Order Management API
  As a pet store API user
  I want to manage store orders
  So that I can handle pet purchase transactions

  Background:
    Given the Petstore API is available at "https://petstore.swagger.io/v2"

  @positive @orders @api
  Scenario: Place a new order successfully
    Given I have a valid order payload for pet ID 1 with quantity 2
    When I send a POST request to "/store/order" endpoint
    Then the response status code should be 200
    And the response should contain order details
    And the order should have a valid order ID
    And the order status should be "placed"

  @positive @orders @api
  Scenario: Retrieve order by ID
    Given an order with ID 1 exists in the store
    When I send a GET request to "/store/order/1" endpoint
    Then the response status code should be 200
    And the response should contain order details
    And the response should have valid order schema

  @positive @orders @api
  Scenario: Get store inventory
    When I send a GET request to "/store/inventory" endpoint
    Then the response status code should be 200
    And the response should be a JSON object
    And the response should contain inventory counts

  @negative @orders @api
  Scenario: Place order with invalid pet ID
    Given I have an order payload with invalid pet ID -1
    When I send a POST request to "/store/order" endpoint
    Then the response status code should be 200
    And the response should contain order details

  @negative @orders @api
  Scenario: Place order with missing required fields
    Given I have an incomplete order payload missing pet ID
    When I send a POST request to "/store/order" endpoint
    Then the response status code should be 400
    And the response should contain validation error about missing fields

  @negative @orders @api
  Scenario: Retrieve non-existent order
    When I send a GET request to "/store/order/999999" endpoint
    Then the response status code should be 404

  @negative @orders @api
  Scenario: Delete non-existent order
    When I send a DELETE request to "/store/order/999999" endpoint
    Then the response status code should be 404

  @negative @orders @api
  Scenario: Place order with missing required fields
    Given I have an incomplete order payload missing pet ID
    When I send a POST request to "/store/order" endpoint
    Then the response status code should be 400
    And the response should contain validation error about missing fields
