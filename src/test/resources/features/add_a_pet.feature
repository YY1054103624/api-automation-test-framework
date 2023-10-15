@Regression
Feature: Add pets
  @AddPet
  Scenario Outline: Add a new pet
    Given User has login into system
    When User sends a request to add a new pet <petName> <categoryName>
    |endpoint|method|contendType     |requestBodyFileName   |
    |/pet    |post  |application/json|add_a_pet.json        |
    Then Verify status code is 200
    Then Save data from response
    |id   |category.name|name|
    |petId|categoryName |petName|

    # Get a pet by id
    When User sends a request to get the pet
      |endpoint      |method|contendType|requestBodyFileName|
      |/pet/{{petId}}|get   |none       |none               |
    Then Verify status code is 200
    Then Verify fields from response body
    |id   |category.name|name|
    |petId|categoryName     |petName|

    # Delete a pet by id
    When User sends a request to delete the pet
      |endpoint      |method|contendType|requestBodyFileName|
      |/pet/{{petId}}|delete|none       |none               |
    Then Verify status code is 200

    Examples:
    | petName | categoryName |
    | "Dabao" | "Cat"        |
    | "Gouzi" | "Dog"        |