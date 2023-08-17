@AddPet
Feature: Add a new pet
  Scenario: Add a new pet
    Given User has login into system
    When User sends a request
    |endpoint|urlParam|method|contendType     |requestBody   |fieldsToAutoGenerate|fieldsGetFromResponse|urlParamGetFromResponse|
    |/pet    |none    |post  |application/json|add_a_pet.json|id                  |none                 |none                   |
    Then Verify status code is 200
    Then Save data from response "id"

  Scenario: Get a pet by id
    Given User has login into system
    When User sends a request
      |endpoint|urlParam|method|contendType     |requestBody   |fieldsToAutoGenerate|fieldsGetFromResponse|urlParamGetFromResponse|
      |/pet    |/?      |get   |none            |none          |none                |none                 |id                     |
    Then Verify status code is 200

  Scenario: Delete a pet by id
    Given User has login into system
    When User sends a request
      |endpoint|urlParam|method|contendType     |requestBody   |fieldsToAutoGenerate|fieldsGetFromResponse|urlParamGetFromResponse|
      |/pet    |/?      |delete|none            |none          |none                |none                 |id                     |
    Then Verify status code is 200
