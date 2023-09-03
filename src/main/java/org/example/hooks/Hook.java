package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static org.example.environment.EnvironmentVariables.initEnvironmentVariables;
import static org.example.environment.EnvironmentVariables.unLoadEnvironmentVariables;

/**
 * This class is for setup and clearing.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
public final class Hook {
    /**
     * Invoke to initialize the environment variable.
     */
    @Before()
    public void beforeScenario() {
        initEnvironmentVariables();
    }

    /**
     * Invoke to remove the environment variable.
     */
    @After()
    public void afterScenario() {
        unLoadEnvironmentVariables();
    }
}
