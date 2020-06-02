package cucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/RatingsApiLatest.feature",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = "stepDefinitions")
public class TestRunner {}
