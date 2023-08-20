@Regression
Feature: Update a pet
  @UpdatePet
  Scenario: Add a new pet
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType     |requestBody   |
      |/pet    |post  |application/json|add_a_pet.json|
    Then Verify status code is 200
    Then Save data from response
    |id|
    |petId|

  Scenario: Update a pet by id
    Given User has login into system
    When User sends a request
      |endpoint      |method|contendType                      |requestBody      |
      |/pet/{{petId}}|post  |application/x-www-form-urlencoded|update_a_pet.json|
    Then Verify status code is 200

  Scenario: Get a pet by id
    Given User has login into system
    When User sends a request
      |endpoint      |method|contendType|requestBody|
      |/pet/{{petId}}|get   |none       |none       |
    Then Verify status code is 200

  Scenario: Delete a pet by id
    Given User has login into system
    When User sends a request
      |endpoint      |method|contendType|requestBody|
      |/pet/{{petId}}|delete|none       |none       |
    Then Verify status code is 200
