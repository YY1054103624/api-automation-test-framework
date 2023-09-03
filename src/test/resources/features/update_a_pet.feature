@Regression
Feature: Update pets
  @UpdatePet
  Scenario: Update a pet
    Given User has login into system
    When User sends a request
      |endpoint|method|contendType     |requestBodyFileName|
      |/pet    |post  |application/json|add_a_pet.json     |
    Then Verify status code is 200
    Then Save data from response
    |id|
    |petId|

    # Update a pet by id
    When User sends a request
      |endpoint      |method|contendType                      |requestBodyFileName|
      |/pet/{{petId}}|post  |application/x-www-form-urlencoded|update_a_pet.json  |
    Then Verify status code is 200

    # Get a pet by id
    When User sends a request
      |endpoint      |method|contendType|requestBodyFileName|
      |/pet/{{petId}}|get   |none       |none               |
    Then Verify status code is 200

    # Delete a pet by id
    When User sends a request
      |endpoint      |method|contendType|requestBodyFileName|
      |/pet/{{petId}}|delete|none       |none               |
    Then Verify status code is 200
