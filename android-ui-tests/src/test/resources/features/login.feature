Feature: User Login Functionality
  As a user of the demo app
  I want to be able to login with valid credentials
  So that I can access the application features

  Background:
    Given the demo app is launched
    And I am on the login screen

  @positive @login
  Scenario: Successful login with valid credentials
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I tap the login button
    Then I should be redirected to the products page
    And I should see the products list

  @positive @login
  Scenario: Login form validation with valid inputs
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    Then the login button should be enabled
    And no error messages should be displayed

  @negative @login
  Scenario: Login with invalid username
    When I enter username "invalid_user"
    And I enter password "secret_sauce"
    And I tap the login button
    Then I should see an error message "Username and password do not match any user in this service"
    And I should remain on the login screen

  @negative @login
  Scenario: Login with empty credentials
    When I leave username field empty
    And I leave password field empty
    And I tap the login button
    Then I should see an error message "Username is required"
    And I should remain on the login screen

  @negative @login
  Scenario: Login with locked user
    When I enter username "locked_out_user"
    And I enter password "secret_sauce"
    And I tap the login button
    Then I should see an error message "Sorry, this user has been locked out"
    And I should remain on the login screen