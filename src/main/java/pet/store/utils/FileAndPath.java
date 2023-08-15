package pet.store.utils;

import java.io.File;

public class FileAndPath {
    public static final String JSON_PARAM_DIRECTORY_PATH = "src/test/resources";
    public static File getJsonFileObject(String requestParamFileLocation) {
        return new File(JSON_PARAM_DIRECTORY_PATH + File.separator + requestParamFileLocation);
    }
}
