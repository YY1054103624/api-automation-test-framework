package org.example.constants;

import java.io.File;

public final class FrameworkConstants {
    private FrameworkConstants() {

    }

    private static final String REQUEST_BODIES_FOLDER = "src/main/resources/request_bodies";
    private static final String ENVIRONMENT_PROPERTIES_PATH = "src/test/resources/environment.properties";
    private static final String REGEX_OF_FAKE_DATA_REPLACEMENT = "\\{\\{\\$\\w+?\\.\\w+?}}";
    private static final String REGEX_OF_ENV_DATA_REPLACEMENT = "\\{\\{\\w+?}}";


    public static String getRequestBodyPath(String fileName) {
        return REQUEST_BODIES_FOLDER + File.separator + fileName;
    }

    public static String getRegexOfFakeDataReplacement() {
        return REGEX_OF_FAKE_DATA_REPLACEMENT;
    }

    public static String getRegexOfEnvDataReplacement() {
        return REGEX_OF_ENV_DATA_REPLACEMENT;
    }

    public static String getEnvironmentPropertiesPath() {
        return ENVIRONMENT_PROPERTIES_PATH;
    }


    public static void main(String[] args) {
        File file = new File(ENVIRONMENT_PROPERTIES_PATH);
        System.out.println(file.exists());
    }
}
