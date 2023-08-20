@Regression @AddANewPet
Feature: Add a new pet
  @AddPet
  Scenario: Add a new pet
    Given User has login into system
    When User sends a request
    |endpoint|method|contendType     |requestBody   |
    |/pet    |post  |application/json|add_a_pet.json|
    Then Verify status code is 200
    Then Save data from response
    |id   |category.name|name|
    |petId|category     |petName|

  Scenario: Get a pet by id
    Given User has login into system
    When User sends a request
      |endpoint      |method|contendType|requestBody|
      |/pet/{{petId}}|get   |none       |none       |
    Then Verify status code is 200
    Then Verify fields from response body
    |id   |category.name|name|
    |petId|category     |petName|

  Scenario: Delete a pet by id
    Given User has login into system
    When User sends a request
      |endpoint      |method|contendType|requestBody|
      |/pet/{{petId}}|delete|none       |none       |
    Then Verify status code is 200
