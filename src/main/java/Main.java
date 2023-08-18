import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static pet.store.utils.JsonProcessor.getJSONObject;

public class Main {
    public static void main(String[] args) {
//        try {
//            String contents = Files.readString(Path.of("src/test/resources/Gpath.json"));
//            System.out.println(contents);
//            System.out.println(contents.replace("{{count}}", "20"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Map<String, String> map = Map.of("count", "20");
//        JSONObject jsonObject = getJSONObject("Gpath.json");
//        String j = jsonObject.toString();
//        for (Map.Entry<String, String> entry: map.entrySet()) {
//            if (NumberUtils.isCreatable(entry.getValue())) {
//
//                System.out.println(j.replace("{{" + entry.getKey() + "}}", entry.getValue()));
//            }
//        }
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
    }
}
