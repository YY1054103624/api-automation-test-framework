@Regression
Feature: Place orders
  @PlaceOrder
  Scenario: Place an order
    # Add a new pet
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType     |requestBodyFileName|
      |/pet    |post  |application/json|add_a_pet.json     |
    Then Verify status code is 200
    Then Save data from response
    |id|
    |petId|

    # Place an order
    When User sends a request
      |endpoint        |method|contendType     |requestBodyFileName|
      |/store/order    |post  |application/json|place_an_order.json|
    Then Verify status code is 200
    Then Save data from response
    |id|
    |orderId|

    # Get an order by order id
    When User sends a request
      |endpoint                |method|contendType|requestBodyFileName|
      |/store/order/{{orderId}}|get   |none       |none       |
    Then Verify status code is 200

