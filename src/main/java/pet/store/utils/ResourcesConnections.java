package pet.store.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static pet.store.utils.FileAndPath.*;

public class ResourcesConnections {
    public static final Properties env = getProperty(RESOURCES_DIRECTORY_PATH, "Env.properties");
    public static final Map<String, String> temp = new HashMap<>();
    public static final FileOutputStream fos = getFileOutPutStream(RESOURCES_DIRECTORY_PATH, "Env.properties");

    public static void main(String[] args) throws IOException {
        env.setProperty("name", "Oscar");
        env.setProperty("category", "Plush");
        FileOutputStream fos = new FileOutputStream(RESOURCES_DIRECTORY_PATH + File.separator + "Env.properties");
        env.store(fos, "Save variables into property file");
        fos.close();
        System.out.println(env.getProperty("baseUrl"));
    }
}
