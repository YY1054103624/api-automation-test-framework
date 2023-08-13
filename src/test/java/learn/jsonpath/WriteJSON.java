package learn.jsonpath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteJSON {
    public static void main(String[] args) {
        JSONObject jsonObject0 = new JSONObject();
        jsonObject0 .put("id", "1");
        jsonObject0.put("name", "Active Wear - Men");
        jsonObject0.put("description", "H+ ACTIVE Apparel-Men");


        JSONObject jsonObject1 = new JSONObject();
        jsonObject1 .put("id", "3");
        jsonObject1.put("name", "Active Wear - Unisex");
        jsonObject1.put("description", "H+ ACTIVE Apparel-Unisex");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2 .put("id", "2");
        jsonObject2.put("name", "Active Wear - Women");
        jsonObject2.put("description", "H+ ACTIVE Apparel-Women");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject0);
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("records", jsonArray);

        File file = new File("resources/response.json");
        FileWriter fw = null;

        try {
            fw = new FileWriter(file);
            fw.write(jsonObject.toString());
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
