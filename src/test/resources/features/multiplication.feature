@calculator
Feature:
  Scenario Outline: verify the multiplication functionality of my Calculator
    Given My calculator has been setup
    When User inputs the first number <first_number>
    * User inputs the second number <second_number>
      | name  | age |
      | Oscar | 29  |
    * User clicks on * button
    Then Verify user should see then result of <result>

    Examples:
      | first_number | second_number | result |
      | 1            | 2             | 3      |
      | 2            | 2             | 4      |
      | 3            | 2             | 6      |
