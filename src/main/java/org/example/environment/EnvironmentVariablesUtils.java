package org.example.environment;

import org.example.environment.EnvironmentVariablesManager;

import java.util.Map;
import java.util.Objects;

public final class EnvironmentVariablesUtils {
    private EnvironmentVariablesUtils() {

    }

    public static String getValue(String key) {
        if (Objects.isNull(key) || !EnvironmentVariablesManager.get().containsKey(key))
            throw new RuntimeException("Key isn't found.");
        return EnvironmentVariablesManager.get().get(key);
    }

    public static void putValue(String key, String value) {
        if (Objects.isNull(key))
            throw new RuntimeException("Key cannot be null.");
        EnvironmentVariablesManager.get().put(key, value);
    }

    public static Map<String, String> getAllVariables() {
        return EnvironmentVariablesManager.get();
    }
}
