package pet.store.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static pet.store.utils.FileAndPath.getJsonFileObject;

import java.io.File;

public class Pet {
    @Given("User has login into system")
    public void user_has_login_into_system() {
    }
    @When("User send request to add a new pet with {string}")
    public void user_send_request_to_add_a_new_pet(String requestParamFileLocation) {
        File paramFile = getJsonFileObject(requestParamFileLocation);
    }
    @Then("Verify status code is {int}")
    public void verify_status_code_is(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
