package PankilSharma.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/PankilSharma.Cucumber", glue = "src/test/java/PankilSharma.StepDefinitions", monochrome = true, plugin = {
		"html:target/cucumber.html" })
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
