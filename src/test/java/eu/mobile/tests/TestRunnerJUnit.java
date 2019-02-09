package eu.mobile.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features path relative to project root
        features = "src/test/java/eu/mobile/features",
        glue = {"eu.mobile.step_definitions"},
        plugin = {
                "pretty",
                "html:target/cucumber"
        },
        tags = "@tasks"
)
public class TestRunnerJUnit {
}
