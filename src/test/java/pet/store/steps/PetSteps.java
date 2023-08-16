package pet.store.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static pet.store.utils.FileAndPath.getJsonFileObject;
import static pet.store.utils.FileAndPath.getJsonFilePath;
import static pet.store.utils.JsonProcessor.getRequestParam;
import static pet.store.utils.ResourcesConnections.env;
import static pet.store.utils.ResourcesConnections.temp;
import static pet.store.utils.SendRequest.sendRequest;

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
    public void user_send_request_to_with_from_last_interface_and(String string, String string2, String string3, String string4) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("Verify status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        response.assertThat().statusCode(expectedStatusCode);
    }

    @Then("Save data from response")
    public void save_data_from_response(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> map = dataTable.asMaps().get(0);
        for (Map.Entry<String, String> entry: map.entrySet()) {
            temp.put(entry.getValue(), response.extract().jsonPath().get(entry.getKey()));
        }
        System.out.println(temp.get("petId"));
    }

}
