package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.example.environment.EnvironmentVariablesUtils;
import java.util.Map;
import static org.example.utils.SendRequestUtils.sendRequest;
import static org.assertj.core.api.BDDAssertions.then;

public class PetSteps {
    private ValidatableResponse response;
    @Given("User has login into system")
    public void user_has_login_into_system() {
    }

    @When("User sends a request")
    public void user_sends_a_request(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestSettings = dataTable.asMaps().get(0);
        response = sendRequest(requestSettings);
    }

    @Then("Verify status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        then(response).matches(r -> r.extract().statusCode() == expectedStatusCode);
    }

    @Then("Verify fields from response body")
    public void verify_fields_from_response_body(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            then(response).
                    matches(r -> r.extract().jsonPath().get(entry.getKey()).toString()
                            .equals(EnvironmentVariablesUtils.getValue(entry.getValue())));
        }
    }

    @Then("Save data from response")
    public void save_data_from_response(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            EnvironmentVariablesUtils.putValue(entry.getValue(), response.extract().jsonPath().get(entry.getKey()).toString());
        }
    }

}
