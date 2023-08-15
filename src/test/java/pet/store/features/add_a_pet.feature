Feature: Add a new pet
  Scenario: Add a new pet
    Given User has login into system
    When User send request to add a new pet with "add_a_pet.json"
    Then Verify status code is 200