@ShoppingCentre
Feature: Shopping centre
  Scenario: login into system
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType                   |requestBody               |
      |/login  |post  |application/json;charset=UTF-8|shopping_centre_login.json|
    Then Verify status code is 200
    Then Save data from response
    |data.token|
    |token     |

  Scenario: get home page info
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType|requestBody|
      |/info   |get   |none       |none       |
    Then Verify status code is 200

  Scenario: logout
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType|requestBody|
      |/logout |post  |none       |none       |
    Then Verify status code is 200

