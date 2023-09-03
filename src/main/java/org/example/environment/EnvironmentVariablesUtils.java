package org.example.environment;

import org.example.exceptions.FrameworkException;

import java.util.Map;
import java.util.Objects;

/**
 * This class opens interface for using the environment variable outside.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class EnvironmentVariablesUtils {
    private EnvironmentVariablesUtils() {

    }

    /**
     * Get a value from the environment variable by key.
     * @param key {@link java.lang.String} to access related value.
     * @return {@link java.lang.String}.
     */
    public static String getValue(String key) {
        if (Objects.isNull(key) || !EnvironmentVariablesManager.get().containsKey(key))
            throw new FrameworkException("Key isn't found.");
        return EnvironmentVariablesManager.get().get(key);
    }

    /**
     * Save key-value into the environment variable.
     * @param key {@link java.lang.String}
     * @param value {@link java.lang.String}
     */
    public static void putValue(String key, String value) {
        if (Objects.isNull(key))
            throw new FrameworkException("Key cannot be null.");
        EnvironmentVariablesManager.get().put(key, value);
    }

    /**
     * Get the environment variable.
     * @return the environment variable.
     */
    public static Map<String, String> getAllVariables() {
        return EnvironmentVariablesManager.get();
    }
}
