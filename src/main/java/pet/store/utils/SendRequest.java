package pet.store.utils;

import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.RequestSenderOptions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        RequestSpecification requestSpecification = given();

        String endpoint = requestSettings.get("endpoint");
        String method = requestSettings.get("method");
        String contentType = requestSettings.get("contendType");
        String requestBody = requestSettings.get("requestBody");

        // if endpoint is needed
        if (!"none".equalsIgnoreCase(endpoint)) {
            // Process endpoint
            endpoint = getReplacedFakeData(getReplaceDataFromEnv(endpoint));
        }

        // if contentType is needed
        if (!"none".equalsIgnoreCase(contentType)) {
            requestSpecification = requestSpecification.contentType(contentType);
        }

        // if requestBody is needed
        if (!"none".equalsIgnoreCase(requestBody)) {
            // Process requestBody
            requestBody = getReplacedFakeData(getReplaceDataFromEnv(getRequestBodyFromFile(RESOURCES_DIRECTORY_PATH, requestBody)));
            requestSpecification = requestSpecification.body(requestBody);
        }

        // if Authorization is needed
        if (!endpoint.equalsIgnoreCase("/login") && env.containsKey("token")) {
            requestSpecification = requestSpecification.header("Authorization", "Bearer " + env.getProperty("token"));
        }

        try {
            // invoke specific http method by reflection
            Class c = RequestSpecificationImpl.class;
            Method httpMethod = c.getDeclaredMethod(method.toLowerCase(), String.class, Object[].class);
            // new Object[0]: invoke method with variadic parameters as empty
            Response r = (Response) httpMethod.invoke(requestSpecification, endpoint, new Object[0]);
            response = r.then().log().body();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
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
