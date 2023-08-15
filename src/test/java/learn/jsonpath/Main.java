package learn.jsonpath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pet.store.utils.FileAndPath;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) {
        File file = FileAndPath.getJsonFileObject("add_a_pet.json");
        System.out.println(file.exists());
    }
}
