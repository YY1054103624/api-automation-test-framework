@UpdatePet
Feature: Add a new pet
  @AddPets
  Scenario: Add a new pet
    Given User has login into system
    When User send "post" request to "/pet" with "add_a_pet.json" as parameter
      |id|
      |AUTO|
    Then Verify status code is 200
    Then Save data from response
      |id|
      |petId|

  @GetPet
  Scenario: Get a pet by id
    Given User has login into system
    When User send "get" request to "/pet" with "petId" from last interface
    Then Verify status code is 200

  @DeletePet
  Scenario: Delete a pet by id
    Given User has login into system
    When User send "delete" request to "/pet" with "petId" from last interface
    Then Verify status code is 200
