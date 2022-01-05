package runners;

import io.cucumber.guice.GuiceFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.epam.reportportal.cucumber.ScenarioReporter",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "progress",
                "summary"
        },
        monochrome = true,
        features = "src/test/resources/feature",
        tags = "@google",
        glue = {""},
        objectFactory = GuiceFactory.class
)
public class CucumberRunnerTest {
}
