Feature: Pet Management API
  As a pet store API user
  I want to manage pets in the store
  So that I can add, update, retrieve and delete pet information

  Background:
    Given the Petstore API is available at "https://petstore.swagger.io/v2"

  @positive @pets @api
  Scenario: Create a new pet successfully
    Given I have a valid pet payload with name "Buddy" and status "available"
    When I send a POST request to "/pet" endpoint
    Then the response status code should be 200
    And the response should contain pet name "Buddy"
    And the pet should have a valid ID

  @positive @pets @api
  Scenario: Retrieve an existing pet by ID
    Given a pet with ID 1 exists in the store
    When I send a GET request to "/pet/1" endpoint
    Then the response status code should be 200
    And the response should contain pet details
    And the response should have valid pet schema

  @positive @pets @api
  Scenario: Update an existing pet
    Given a pet with ID 1 exists in the store
    And I have updated pet payload with name "Updated Buddy" and status "sold"
    When I send a PUT request to "/pet" endpoint
    Then the response status code should be 200
    And the response should contain pet name "Updated Buddy"
    And the response should contain status "sold"

  @positive @pets @api
  Scenario: Find pets by status
    Given pets exist with status "available"
    When I send a GET request to "/pet/findByStatus" with status "available"
    Then the response status code should be 200
    And the response should be an array
    And all pets in response should have status "available"

  @positive @pets @api
  Scenario: Upload pet image
    Given a pet with ID 1 exists in the store
    And I have a valid image file
    When I send a POST request to "/pet/1/uploadImage" with the image
    Then the response status code should be 200
    And the response should contain upload confirmation message

  @negative @pets @api
  Scenario: Create pet with invalid data
    Given I have an invalid pet payload with missing required fields
    When I send a POST request to "/pet" endpoint
    Then the response status code should be 200
    And the response should contain validation error

  @negative @pets @api
  Scenario: Update pet with invalid ID
    Given I have a pet payload with invalid ID -1
    When I send a PUT request to "/pet" endpoint
    Then the response status code should be 200
    And the response should contain validation error

  @negative @pets @api
  Scenario: Find pets with invalid status
    When I send a GET request to "/pet/findByStatus" with status "invalid_status"
    Then the response status code should be 200
    And the response should contain validation error for status

  @negative @pets @api
  Scenario: Retrieve non-existent pet
    When I send a GET request to "/pet/999999" endpoint
    Then the response status code should be 404

  @negative @pets @api
  Scenario: Delete non-existent pet
    When I send a DELETE request to "/pet/999999" endpoint
    Then the response status code should be 404