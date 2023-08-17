@PlaceOrder
Feature: Add a new pet
  Scenario: Add a new pet
    Given User has login into system
    When User sends a request
      |endpoint|urlParam|method|contendType     |requestBody   |fieldsToAutoGenerate|fieldsGetFromResponse|urlParamGetFromResponse|
      |/pet    |none    |post  |application/json|add_a_pet.json|id                  |none                 |none                   |
    Then Verify status code is 200
    Then Save data from response "id"

  Scenario: Place an order
    Given User has login into system
    When User sends a request
      |endpoint        |urlParam|method|contendType     |requestBody        |fieldsToAutoGenerate|fieldsGetFromResponse|urlParamGetFromResponse|
      |/store/order    |none    |post  |application/json|place_an_order.json|id                  |id->petId            |none                   |
    Then Verify status code is 200
    Then Save data from response "id"

  Scenario: Get an order by orderId
    Given User has login into system
    When User sends a request
      |endpoint    |urlParam|method|contendType     |requestBody   |fieldsToAutoGenerate|fieldsGetFromResponse|urlParamGetFromResponse|
      |/store/order|/?      |get   |none            |none          |none                |none                 |id                     |
    Then Verify status code is 200

