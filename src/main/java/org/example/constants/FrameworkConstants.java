package org.example.constants;

import java.io.File;

/**
 * Framework constants.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class FrameworkConstants {
    private FrameworkConstants() {

    }

    private static final String REQUEST_BODIES_FOLDER = "src/main/resources/request_bodies";
    private static final String ENVIRONMENT_PROPERTIES_PATH = "src/test/resources/environment.properties";
    private static final String REGEX_OF_FAKE_DATA_REPLACEMENT = "\\{\\{\\$\\w+?\\.\\w+?}}";
    private static final String REGEX_OF_ENV_DATA_REPLACEMENT = "\\{\\{\\w+?}}";

    /**
     * Get the file path of request body.
     * @param fileName
     * @return file path.
     */
    public static String getRequestBodyPath(String fileName) {
        return REQUEST_BODIES_FOLDER + File.separator + fileName;
    }

    /**
     * Get the regex for fake data.
     * @return regex of fake data
     */
    public static String getRegexOfFakeDataReplacement() {
        return REGEX_OF_FAKE_DATA_REPLACEMENT;
    }

    /**
     * Get the regex for env data.
     * @return the regex of env data.
     */
    public static String getRegexOfEnvDataReplacement() {
        return REGEX_OF_ENV_DATA_REPLACEMENT;
    }

    /**
     * Get the file path of environment.properties.
     * @return file path.
     */
    public static String getEnvironmentPropertiesPath() {
        return ENVIRONMENT_PROPERTIES_PATH;
    }
}
