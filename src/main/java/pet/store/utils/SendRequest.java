package pet.store.utils;

import io.restassured.response.ValidatableResponse;

import java.io.File;

import static io.restassured.RestAssured.given;


public class SendRequest {
    public static ValidatableResponse post(String requestParamFileLocation) {
        File jsonFile = FileAndPath.getJsonFileObject(requestParamFileLocation);
        return given().when().get().then().log().body();
    }
}
