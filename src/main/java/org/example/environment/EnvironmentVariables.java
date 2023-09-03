package org.example.environment;

import org.example.utils.PropertiesUtils;

import java.util.Map;
import java.util.Objects;

public final class EnvironmentVariables {
    private EnvironmentVariables() {

    }

    public static void initEnvironmentVariables() {
        if (Objects.isNull(EnvironmentVariablesManager.get())) {
            EnvironmentVariablesManager.set(PropertiesUtils.getEnvironmentVariablesAsMap());
        }
    }

    public static void unLoadEnvironmentVariables() {
        PropertiesUtils.saveRuntimeVariables();
        EnvironmentVariablesManager.remove();
    }
}
