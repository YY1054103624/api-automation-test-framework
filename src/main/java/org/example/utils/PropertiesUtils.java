package org.example.utils;

import org.example.environment.EnvironmentVariablesUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.constants.FrameworkConstants.getEnvironmentPropertiesPath;

public final class PropertiesUtils {
    private PropertiesUtils() {

    }

    private static final Properties PROPERTIES = new Properties();

    static {
        try(FileInputStream fis = new FileInputStream(getEnvironmentPropertiesPath())) {
            PROPERTIES.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public static void saveRuntimeVariables() {
        try(FileOutputStream fos = new FileOutputStream(getEnvironmentPropertiesPath())) {
            for (Map.Entry<String, String> entry: EnvironmentVariablesUtils.getAllVariables().entrySet()) {
                PROPERTIES.setProperty(entry.getKey(), entry.getValue());
            }
            PROPERTIES.store(fos, null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
