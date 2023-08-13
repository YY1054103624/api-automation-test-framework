package learn.jsonpath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) {
        baseURI = "https://petstore.swagger.io/v2";
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("username", "admin");
        hashMap.put("password", "password123");
        Response response = null;
        FileReader fr = null;
        try {
            fr = new FileReader("./src/main/resources/create_user.json");
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fr);
            response = given().contentType(ContentType.JSON).body(jsonObject).post("/user");
            int code = response.jsonPath().get("code");
            System.out.println(code);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
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
