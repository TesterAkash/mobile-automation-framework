Feature: Mobile App End-to-End Scenarios

  @smoke @regression
  Scenario: 1. Successful login with valid credentials
    Given I launch the Sauce Labs mobile application
    When I log in with username "standard_user" and password "secret_sauce"
    Then I should see the product catalog screen

  @regression
  Scenario Outline: 2. Unsuccessful login with invalid credentials
    Given I launch the Sauce Labs mobile application
    When I log in with username "<username>" and password "<password>"
    Then I should see an error message containing "<error_message>"

    Examples:
      | username        | password     | error_message                                              |
      | locked_out_user | secret_sauce | Sorry, this user has been locked out.                      |
      | invalid_user    | wrong_pass   | Username and password do not match any user in this service |

  @smoke @regression
  Scenario: 3. Successful logout
    Given I launch the Sauce Labs mobile application
    When I log in with username "standard_user" and password "secret_sauce"
    And I open the menu and tap logout
    Then I should be navigated back to the login screen

  @regression @functional
  Scenario: 4. Add item to cart
    Given I launch the Sauce Labs mobile application
    When I log in with username "standard_user" and password "secret_sauce"
    And I add the first product to the cart
    And I navigate to the cart page
    Then I should see the item listed in the cart