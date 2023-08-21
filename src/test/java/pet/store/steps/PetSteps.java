package pet.store.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static pet.store.utils.JsonProcessor.getJSONObject;
import static pet.store.utils.JsonProcessor.getRequestParam;
import static pet.store.utils.ResourcesConnections.*;
import static pet.store.utils.SendRequest.sendRequest;
import static pet.store.utils.SendRequest.sendRequest2;
import static org.junit.Assert.*;
import java.util.concurrent.locks.*;

public class PetSteps {
    private ValidatableResponse response;
    @Given("User has login into system")
    public void user_has_login_into_system() {
    }
    @When("User send {string} request to {string} with {string} as parameter")
    public void user_send_request_to_add_a_new_pet_with(String httpMethod, String endpoint, String param, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps();
        Map<String, String> map = list.get(0);
        param = getRequestParam(param, map);
        try {
            response = sendRequest(httpMethod, endpoint, param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @When("User send {string} request to {string} with {string} from last interface")
    public void user_send_request_to_with_data_from_last_interface(String httpMethod, String endpoint, String key) {
        endpoint = endpoint + "/" + temp.get(key);
        try {
            response = sendRequest(httpMethod, endpoint, "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("User send {string} request to {string} with {string} from last interface and {string}")
    public void user_send_request_to_with_from_last_interface_and(String httpMethod, String endpoint, String key, String fileName) {
        endpoint = endpoint + "/" + temp.get(key);
        response = given().contentType("application/x-www-form-urlencoded").body(getJSONObject(fileName).toString()).when().post(endpoint).then().log().body();
    }

    @When("User sends a request")
    public void user_sends_a_request(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> requestSettings = dataTable.asMaps().get(0);
        response = sendRequest2(requestSettings);
    }

    @Then("Verify status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        try {
            response.assertThat().statusCode(expectedStatusCode);
        } catch (AssertionError e) {
            response.log().all();
            throw e;
        }
    }

    @Then("Verify fields from response body")
    public void verify_fields_from_response_body(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            String actual = response.extract().jsonPath().get(entry.getKey()).toString();
            String expect = env.getProperty(entry.getValue());
            System.out.println("actual: " + actual);
            System.out.println("expect: " + expect);
            try {
                assertEquals(expect, actual);
            } catch (AssertionError e) {
                response.log().everything();
                throw new AssertionError(e.getMessage());
            }
        }
    }

    @Then("Save data from response")
    public void save_data_from_response(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            env.setProperty(entry.getValue(), response.extract().jsonPath().get(entry.getKey()).toString());
        }
    }

}
