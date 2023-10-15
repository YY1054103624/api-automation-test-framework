package org.example.environment;

import org.example.utils.PropertiesUtils;
import java.util.Objects;

/**
 * This class works for initializing and clearing the environment variable object.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class EnvironmentVariables {
    private EnvironmentVariables() {

    }

    /**
     * Initialize the environment variable object.
     */
    public static void initEnvironmentVariables() {
        if (Objects.isNull(EnvironmentVariablesManager.get())) {
            EnvironmentVariablesManager.set(PropertiesUtils.getEnvironmentVariablesAsMap());
        }
    }

    /**
     * Clear the environment variable object.
     */
    public static void unLoadEnvironmentVariables() {
        EnvironmentVariablesManager.remove();
    }
}
