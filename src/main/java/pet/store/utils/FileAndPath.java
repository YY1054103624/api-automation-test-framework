package pet.store.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class FileAndPath {
    public static final String RESOURCES_DIRECTORY_PATH = "src/test/resources";
    public static File getJsonFileObject(String requestParamFileLocation) {
        return new File(getJsonFilePath(requestParamFileLocation));
    }

    public static String getJsonFilePath(String fileName) {
        return RESOURCES_DIRECTORY_PATH + File.separator + fileName;
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

    public static String getRequestBodyFromFile(String dirPath, String fileName) {
        Path filePath = Path.of(dirPath + File.separator + fileName);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("file doesn't exist: " + filePath.toString());
            throw new RuntimeException(e);
        }
    }
}
