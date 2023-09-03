Api Automation Test Framework
========================

This framework is built with Java-Rest Assured-Cucumber-assertj, using BDD style.

Features
--------------
| Operator                  | Description                                                         |
| :------------------------ | :------------------------------------------------------------------ |
| Parallel                  | Execute simultaneously.                                             |
| Flexible                  | Config which test cases you are going to execute by Tags.           |
| BDD style                 | Use human language to write test cases.                             |
| Fake data                 | Integrated with [datafaker](https://www.datafaker.net/).            |
| Simulate Postman          | Use {{name}}/{{$name}} to access environment variables or fake data.|

Precondition
---------------
Before you start using this framework, make sure you have installed these tools:
- Java
- Maven

Getting stated
---------------
Pull the code down to your local environment.

I have already created three features for demo, you can run this framework with a maven command:
```tcsh
mvn -Dtest=TestRunner test
```

Then you can find the timeline reprot under target/generated-timeline-report and html report under target/generated-html-report.

Run your own test cases
-----------------
Simulate the features file I've already created.
```gherkin
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
```
