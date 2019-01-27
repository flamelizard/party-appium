package eu.mobile.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// TODO run appium by code, appium github sample code
// https://github.com/appium/appium/tree/master/sample-code/java
// TODO run with TestNG, supply Cucumber/test opts from testng.xml
@RunWith(Cucumber.class)
@CucumberOptions(
//        TODO path is not relative to project root
        features = "C:\\devel\\projects\\appium\\src\\test\\java\\eu\\mobile\\features",
        glue = "eu.mobile.step_definitions",
        plugin = {
                "pretty",
                "html:target/cucumber"
        },
        tags = "@tasks"
)
public class RunSampleTest {
}
// https://medium.com/agile-vision/cucumber-bdd-part-2-creating-a-sample-java-project-with-cucumber-testng-and-maven-127a1053c180
