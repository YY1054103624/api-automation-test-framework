package pet.store.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import static pet.store.utils.FileAndPath.getProperty;

public class ResourcesConnections {
    public static final String RESOURCES_DIR_PATH = "src/test/resources";
    public static final Properties env = getProperty(RESOURCES_DIR_PATH, "Env.properties");
    public static final Map<String, String> temp = new HashMap<>();
    public static void main(String[] args) {
        System.out.println(env.get("baseUrl"));
    }
}
