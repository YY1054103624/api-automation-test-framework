package cucumber.hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static pet.store.utils.FileAndPath.RESOURCES_DIRECTORY_PATH;
import static pet.store.utils.FileAndPath.getProperty;
import static pet.store.utils.ResourcesConnections.*;

public class PetHooks {
    private static StopWatch stopWatch = new StopWatch();

    @AfterAll
    public static void after_all() {
        try {
            env.store(fos, "Save variables into property file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//        System.out.println(String.format("Total time %d ms", stopWatch.getTime()));
    }
}
