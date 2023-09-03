import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.example.hooks", "org.example.steps"},
        plugin = {"pretty",
                "timeline:target/generated-timeline-report",
                "html:target/generated-html-report/index.html"},
        tags = "@Regression"
        )
public class TestRunner {
}
