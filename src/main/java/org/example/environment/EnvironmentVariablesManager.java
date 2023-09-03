package org.example.environment;

import java.util.Map;

/**
 * This class creates a ThreadLocal object to manager the environment variable.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class EnvironmentVariablesManager {
    private EnvironmentVariablesManager() {

    }
    private static final ThreadLocal<Map<String, String>> map = new ThreadLocal<>();

    /**
     * Load the environment variable object into ThreadLocal object.
     * @param environmentVariables
     */
    static void set(Map<String, String> environmentVariables) {
        map.set(environmentVariables);
    }

    /**
     * Get the environment variable object from ThreadLocal object.
     * @return
     */
    static Map<String, String> get() {
        return map.get();
    }

    /**
     * Remove the environment variable object from ThreadLocal object.
     */
    static void remove() {
        map.remove();
    }
}
