@Regression
Feature: Add a new pet
  @PlaceOrder
  Scenario: Add a new pet
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType     |requestBody   |
      |/pet    |post  |application/json|add_a_pet.json|
    Then Verify status code is 200
    Then Save data from response
    |id|
    |petId|

  Scenario: Place an order
    Given User has login into system
    When User sends a request
      |endpoint        |method|contendType     |requestBody        |
      |/store/order    |post  |application/json|place_an_order.json|
    Then Verify status code is 200
    Then Save data from response
    |id|
    |orderId|

  Scenario: Get an order by orderId
    Given User has login into system
    When User sends a request
      |endpoint                |method|contendType|requestBody|
      |/store/order/{{orderId}}|get   |none       |none       |
    Then Verify status code is 200

