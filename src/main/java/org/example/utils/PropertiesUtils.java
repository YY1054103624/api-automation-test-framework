package org.example.utils;

import org.example.environment.EnvironmentVariablesUtils;
import org.example.exceptions.FrameworkFileIOException;
import org.example.exceptions.FrameworkFileNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import static org.example.constants.FrameworkConstants.getEnvironmentPropertiesPath;

/**
 * This class is for processing environment.properties.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class PropertiesUtils {
    private PropertiesUtils() {

    }

    private static final Properties PROPERTIES = new Properties();

    static {
        try(FileInputStream fis = new FileInputStream(getEnvironmentPropertiesPath())) {
            PROPERTIES.load(fis);
        } catch (FileNotFoundException e) {
            throw new FrameworkFileNotFoundException("File is not found.", e);
        } catch (IOException e) {
            throw new FrameworkFileIOException("There's an io exception.", e);
        }
    }

    /**
     * Transfer {@link java.util.Properties} object to {@link java.util.HashMap} object.
     * @return {@link java.util.HashMap}.
     */
    public static Map<String, String> getEnvironmentVariablesAsMap() {
        return PROPERTIES
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                e -> String.valueOf(e.getKey()),
                                e -> String.valueOf(e.getValue()),
                                (oldValue, newValue) -> oldValue,
                                HashMap::new
                        )
                );
    }

    /**
     * Store the key-value from the environment variable into environment.properties.
     */
    public static void saveRuntimeVariables() {
        try(FileOutputStream fos = new FileOutputStream(getEnvironmentPropertiesPath())) {
            for (Map.Entry<String, String> entry: EnvironmentVariablesUtils.getAllVariables().entrySet()) {
                PROPERTIES.setProperty(entry.getKey(), entry.getValue());
            }
            PROPERTIES.store(fos, null);
        } catch (FileNotFoundException e) {
            throw new FrameworkFileNotFoundException("File is not found.", e);
        } catch (IOException e) {
            throw new FrameworkFileIOException("There's an io exception", e);
        }
    }
}
