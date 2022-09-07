package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // features we use to provide the path of all the features file
        features = "@target/failed.txt",
        glue = "steps",
        plugin = {"pretty","html:target/cucumber.html"}
)
public class FailedRunner {

}
