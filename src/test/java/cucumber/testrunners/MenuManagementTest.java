package cucumber.testrunners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/cucumber/features", "src/test/java/pet/store/features"},
        glue = {"cucumber.steps", "pet.store.steps"},
        plugin = {"pretty",
        "html:target/SystemTestReports/index.html",
        "json:target/SystemTestReports/json/report.json",
        "junit:target/SystemTestReports/junit/report.xml"},
        tags = "@Pet",
        dryRun = false)
public class MenuManagementTest {
}
