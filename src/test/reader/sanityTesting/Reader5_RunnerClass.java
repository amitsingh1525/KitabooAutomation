package sanityTesting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "features/platform/login.feature",
		glue = {"classpath:com/hurix/test/loginScenario", "classpath:com/hurix/library/kitabooBooks"} , monochrome = true )


public class Reader5_RunnerClass {

}
