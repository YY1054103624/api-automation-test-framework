package pet.store.utils;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static pet.store.utils.ResourcesConnections.env;


public class SendRequest {
    static {
        baseURI = env.getProperty("baseUrl");
    }
    public static ValidatableResponse sendRequest(String httpMethod, String endpoint, String param) throws Exception {
        ValidatableResponse response = null;
        switch (httpMethod.toUpperCase()) {
            case "POST":
                response = given().contentType(ContentType.JSON).body(param).when().post(endpoint).then().log().body();
                break;
            case "GET":
                response = given().when().get(endpoint).then().log().body();
                break;
            case "PUT":
                response = given().contentType(ContentType.JSON).body(param).when().put(endpoint).then().log().body();
                break;
            case "DELETE":
                response = given().when().delete(endpoint).then().log().body();
                break;
            default:
                throw new Exception("method: " + httpMethod + " isn't supported.");
        }
        return response;
    }

    public static void main(String[] args) {
        String filePath = "src/test/resources" + File.separator + "update_a_pet.json";
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
            given().contentType(ContentType.JSON).body(new File(filePath)).when().post("https://petstore.swagger.io/v2" + "/pet/2").then().log().everything();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
