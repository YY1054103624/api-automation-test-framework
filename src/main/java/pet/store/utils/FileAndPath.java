package pet.store.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileAndPath {
    public static final String JSON_PARAM_DIRECTORY_PATH = "src/test/resources";
    public static File getJsonFileObject(String requestParamFileLocation) {
        return new File(getJsonFilePath(requestParamFileLocation));
    }

    public static String getJsonFilePath(String fileName) {
        return JSON_PARAM_DIRECTORY_PATH + File.separator + fileName;
    }

    public static FileReader getFileReader(String fileName) throws FileNotFoundException {
        return new FileReader(getJsonFilePath(fileName));
    }

    public static Properties getProperty(String dirPath, String fileName) {
        FileReader fr = null;
        Properties properties = null;

        try {
            fr = new FileReader(dirPath + File.separator + fileName);
            properties = new Properties();
            properties.load(fr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
        return properties;
    }
}
