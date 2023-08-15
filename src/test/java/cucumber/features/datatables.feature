Feature: Data tables
  @ListofStrings
  Scenario: Bill Amount Generation
    Given I placed an order for the following items
    |Cucumber Sandwich|2|20|
    When I generate the bill
    Then a bill for $40 should be generated

  @TwoRows
  Scenario: Bill Amount Generation
    Given I placed an order for the following items
      |Cucumber Sandwich|2|20|
      |Cucumber Salad|1|15|
    When I generate the bill
    Then a bill for $55 should be generated

  @ListOfMaps
  Scenario: Bill Amount Generation
    Given I placed an order for the following items
      |ItemName         |Units|UnitPrice|
      |Cucumber Sandwich|2    |20       |
      |Cucumber Salad   |1    |15       |
    When I generate the bill
    Then a bill for $55 should be generated
