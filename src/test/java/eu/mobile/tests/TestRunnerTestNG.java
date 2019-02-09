package eu.mobile.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

// TODO run in parallel, add TC, create several test suites and time it
// TODO explore how to measure test coverage
// override options in maven or java environ variable
@CucumberOptions(
        features = "src/test/java/eu/mobile/features/RelocateMe.feature",
        glue = "eu.mobile.step_definitions",
//        tags = "@tasks",
        plugin = {"pretty"},
        // default will skip undefined steps, ultimately passing on missing step defs
        strict = true
)
public class TestRunnerTestNG extends AbstractTestNGCucumberTests {
}
