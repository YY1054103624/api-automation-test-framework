import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.restassured.path.json.JsonPath;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InputStream fr = null;
        try {
            fr = new FileInputStream("src/test/resources/Gpath.json");
//            JsonPath jsonPath = new JsonPath(fr);
//            System.out.println(jsonPath.get("count").toString());
//            System.out.println(jsonPath.get("teams.id[1]").toString());
//            System.out.println(jsonPath.getString("teams.name[-1]"));
//            System.out.println(jsonPath.getList("teams.name").toString());
//
//            Map<String, ?>map = jsonPath.getMap("teams[0]");
//            System.out.println(map.toString());
//            JSONObject jsonObject = (JSONObject) new JSONParser().parse(fr);
//            System.out.println(jsonObject.get("teams[1].name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
