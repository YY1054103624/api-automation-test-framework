package org.example.environment;

import java.util.Map;

public final class EnvironmentVariablesManager {
    private EnvironmentVariablesManager() {

    }
    private static final ThreadLocal<Map<String, String>> map = new ThreadLocal<>();

    static void set(Map<String, String> environmentVariables) {
        map.set(environmentVariables);
    }

    static Map<String, String> get() {
        return map.get();
    }

    static void remove() {
        map.remove();
    }
}
