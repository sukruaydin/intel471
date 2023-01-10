package com.intel471.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com/intel471/step_defs",
        features = "@target/rerun.txt"

)
public class FailedTestRunner {
}
