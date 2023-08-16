package pet.store.utils;

import io.cucumber.java.sl.In;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static pet.store.utils.FileAndPath.getFileReader;
import static pet.store.utils.FileAndPath.getJsonFileObject;

public class JsonProcessor {
    public static String getRequestParam(String fileName, Map<String, String> jsonVariables) {
        JSONObject jsonObject = null;
        jsonObject = getJSONObject(fileName);
        for (Map.Entry<String, String> entry: jsonVariables.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            if ("AUTO".equalsIgnoreCase(value)) {
                long timestamp = System.currentTimeMillis();
                if (jsonObject.get(key) instanceof Number) {
                    jsonObject.put(key, timestamp);
                } else {
                    jsonObject.put(key, "AUTO" + timestamp);
                }
            } else {
                if (jsonObject.get(key) instanceof Number) {
                    if (value.contains(".")) {
                        jsonObject.put(key, Double.valueOf(value));
                    } else {
                        jsonObject.put(key, Integer.valueOf(value));
                    }
                } else {
                    jsonObject.put(key, value);
                }
            }
        }
//            jsonObject.put("petId", 1); System.out.println(jsonObject.get("id"));
        return jsonObject.toString();
    }

    public static JSONObject getJSONObject(String fileName) {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = getFileReader(fileName);
            return (JSONObject) parser.parse(fr);
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

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("id", "2.1");
        System.out.println(getRequestParam("add_a_pet.json", map));
    }
}
