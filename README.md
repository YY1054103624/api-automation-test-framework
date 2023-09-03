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
#### Modify environment.properties
Change baseUrl in environment.properties which is under src/test/resources.

Add key-value that you want to use late in request body or endpoint.

#### Add request bodies
Put request body in a file and paste the file under src/main/resources/request_bodies, like this:
```json
{
  "id": {{$number.positive}},
  "category": {
    "id": 0,
    "name": "{{$cat.breed}}"
  },
  "name": "{{$cat.name}}",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}
```
If a variable is wrapped with {{$}}, like {{$number.positive}}, then it'll be replaced with fake data, refer to [datafaker](https://www.datafaker.net/).

If a variable is wrapped with {{}}, like {{name}}, then it'll be replaced with data with the same key from environment variables. (This doesn't mean you have to put name=? into environment.properties, you can also get the value from another api.)

#### Write test cases
Write a test case by simulating the features file I've already created.
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
Modify **endport**, **method**, **contentType**, **requestBodyFileName** based on the api you are going to send.

If 
