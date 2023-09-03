@Regression
Feature: Add pets
  @AddPet
  Scenario: Add a new pet
    Given User has login into system
    When User sends a request
    |endpoint|method|contendType     |requestBodyFileName   |
    |/pet    |post  |application/json|add_a_pet.json        |
    Then Verify status code is 200
    Then Save data from response
    |id   |category.name|name|
    |petId|category     |petName|

    # Get a pet by id
    When User sends a request
      |endpoint      |method|contendType|requestBodyFileName|
      |/pet/{{petId}}|get   |none       |none               |
    Then Verify status code is 200
    Then Verify fields from response body
    |id   |category.name|name|
    |petId|category     |petName|

    # Delete a pet by id
    When User sends a request
      |endpoint      |method|contendType|requestBodyFileName|
      |/pet/{{petId}}|delete|none       |none               |
    Then Verify status code is 200
