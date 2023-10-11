package runner;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith (Cucumber.class)
@CucumberOptions(
		features= "src/test/resources/Features",
		glue= {"StepDefinitions"},
		monochrome = true,
		plugin = {"json:target/cucumber.json"},
		tags = "@cart"
        )
public class TestRunner {

}
