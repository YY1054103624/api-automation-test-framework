package learn.jsonpath;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJSON {
    public static void main(String[] args) {
        File file = new File("resources/response.json");
        FileReader fr = null;

        try {
            fr = new FileReader(file);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fr);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("records");
            for (Object o: jsonArray) {
                JSONObject jo = (JSONObject) o;
                System.out.println(jo.get("id"));
                System.out.println(jo.get("name"));
                System.out.println(jo.get("description"));
            }
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
