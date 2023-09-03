package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static org.example.environment.EnvironmentVariables.initEnvironmentVariables;
import static org.example.environment.EnvironmentVariables.unLoadEnvironmentVariables;

public final class Hook {
    @Before()
    public void beforeScenario() {
        initEnvironmentVariables();
    }

    @After()
    public void afterScenario() {
        unLoadEnvironmentVariables();
    }
}
