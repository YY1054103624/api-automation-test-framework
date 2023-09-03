import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This class is for starting testing.
 * <p>
* 2023/9/3
* @author Yong Yang
* @version 1.0
* @since 1.0
*/
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
