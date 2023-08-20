package pet.store.utils;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static pet.store.utils.DataProvider.getReplaceDataFromEnv;
import static pet.store.utils.DataProvider.getReplacedFakeData;
import static pet.store.utils.FileAndPath.RESOURCES_DIRECTORY_PATH;
import static pet.store.utils.FileAndPath.getRequestBodyFromFile;
import static pet.store.utils.JsonProcessor.*;
import static pet.store.utils.ResourcesConnections.*;


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

    public static ValidatableResponse sendRequest2(Map<String, String> requestSettings) {
        ValidatableResponse response = null;
        String endpoint = requestSettings.get("endpoint");
        String method = requestSettings.get("method");
        String contentType = requestSettings.get("contendType");
        String requestBody = requestSettings.get("requestBody");

        // Process endpoint
        endpoint = getReplacedFakeData(getReplaceDataFromEnv(endpoint));
        System.out.println("Endpoint is: " + endpoint);

        // Process requestBody
        if ("none".equalsIgnoreCase(requestBody)) {
            requestBody = "";
        } else {
            requestBody = getReplacedFakeData(getReplaceDataFromEnv(getRequestBodyFromFile(RESOURCES_DIRECTORY_PATH, requestBody)));
        }

        switch (method.toUpperCase()) {
            case "POST":
                response = given().contentType(contentType).body(requestBody).when().post(endpoint).then().log().body();
                break;
            case "GET":
                response = given().when().get(endpoint).then().log().body();
                break;
            case "PUT":
                response = given().contentType(contentType).body(requestBody).when().put(endpoint).then().log().body();
                break;
            case "DELETE":
                response = given().when().delete(endpoint).then().log().body();
                break;
            default:
                System.out.println(("method: " + method + " isn't supported."));
        }
        return response;
    }

    public static void main(String[] args) {
//        String fileName = "update_a_pet.json";
//        String method =
//        FileReader fr = null;
//        try {
//            fr = new FileReader(filePath);
//            JSONObject jsonObject = (JSONObject) new JSONParser().parse(fr);
//            int code = given().contentType("application/x-www-form-urlencoded").body(jsonObject.toString()).when().post("https://petstore.swagger.io/v2" + "/pet/3").then().log().everything().extract().statusCode();
////            int code = given().body(jsonObject.toString()).when().post("https://petstore.swagger.io/v2" + "/pet/1").then().log().body().extract().statusCode();
//            System.out.println(code);
//        } catch (IOException | ParseException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (fr != null) {
//                try {
//                    fr.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
    }
}
