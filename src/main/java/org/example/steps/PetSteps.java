package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.example.environment.EnvironmentVariablesUtils;
import java.util.Map;
import static org.example.utils.SendRequestUtils.sendRequest;
import static org.assertj.core.api.BDDAssertions.then;

/**
 * This class is for mapping the steps for Pet.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public class PetSteps {
    private ValidatableResponse response;

    /**
     * This step is for Authorization.
     */
    @Given("User has login into system")
    public void user_has_login_into_system() {
    }

    /**
     * This step is to send a request.
     * @param dataTable
     */
    @When("User sends a request")
    public void user_sends_a_request(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestSettings = dataTable.asMaps().get(0);
        response = sendRequest(requestSettings);
    }

    /**
     * This step is to validate status code.
     * @param expectedStatusCode expected status code.
     */
    @Then("Verify status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        then(response).matches(r -> r.extract().statusCode() == expectedStatusCode);
    }

    /**
     * This step is to validate fields of the api response.
     /
     * @param dataTable all the fields need to be verified.
     */
    @Then("Verify fields from response body")
    public void verify_fields_from_response_body(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            then(response).
                    matches(r -> r.extract().jsonPath().get(entry.getKey()).toString()
                            .equals(EnvironmentVariablesUtils.getValue(entry.getValue())));
        }
    }

    /**
     * This step is to save key-value into the environment variable.
     * @param dataTable key-value that needs to be saved.
     */
    @Then("Save data from response")
    public void save_data_from_response(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            EnvironmentVariablesUtils.putValue(entry.getValue(), response.extract().jsonPath().get(entry.getKey()).toString());
        }
    }

    @When("User sends a request to add a new pet {string} {string}")
    public void userSendsARequestToAddANewPetPetNameCategoryName(String petName, String categoryName, io.cucumber.datatable.DataTable dataTable) {
        EnvironmentVariablesUtils.putValue("petName", petName);
        EnvironmentVariablesUtils.putValue("categoryName", categoryName);
        Map<String, String> requestSettings = dataTable.asMaps().get(0);
        response = sendRequest(requestSettings);
    }

    @When("User sends a request to get the pet")
    public void user_sends_a_request_to_get_the_pet(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestSettings = dataTable.asMaps().get(0);
        response = sendRequest(requestSettings);
    }
    @When("User sends a request to delete the pet")
    public void user_sends_a_request_to_delete_the_pet(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestSettings = dataTable.asMaps().get(0);
        response = sendRequest(requestSettings);
    }

}
