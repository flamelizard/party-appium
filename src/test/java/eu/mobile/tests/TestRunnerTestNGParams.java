package eu.mobile.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import eu.mobile.common.ConfigManager;
import org.testng.annotations.*;

// feature  file and glue set via system property cucumber.options
@CucumberOptions(
//        tags = "@tasks",
        plugin = {"pretty"},
        strict = true
)
// TestNG runner boiler-code
public class TestRunnerTestNGParams {
    private TestNGCucumberRunner testRunner;

    @Parameters({"config", "cucumber.options"})
    @BeforeClass
    public void setUp(String configFile, String CucumberOpts) {
        ConfigManager.setTestConfigFile(configFile);
        ConfigManager.overrideCucumberOptions(CucumberOpts);
        testRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void smokeTasksApp(CucumberFeatureWrapper cFeature) {
        testRunner.runCucumber(cFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testRunner.provideFeatures();
    }

    @AfterClass
    public void cleanUp() {
        testRunner.finish();
    }
}
