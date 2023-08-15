Feature: Menu management
  Background: Add third menu item (Background)
    Given I have a menu item with name "Chicken Sandwich" and price 25
    When I add that menu item
    Then Menu Item with name "Chicken Sandwich" should be added

  @smoke
  Scenario: Add a menu item
    Given I have a menu item with name "Cucumber Sandwich" and price 20
    When I add that menu item
    Then Menu Item with name "Cucumber Sandwich" should be added

  @RegularTest
  Scenario: Add another menu item
    Given I have a menu item with name "Cucumber Salad" and price 15
    When I add that menu item
    Then Menu Item with name "Cucumber Salad" should be added

  @RegularTest @NightlyBuildTest
  Scenario: Add third menu item
    Given I have a menu item with name "Chicken Sandwich" and price 25
    When I add that menu item
    Then Menu Item with name "Chicken Sandwich" should be added
