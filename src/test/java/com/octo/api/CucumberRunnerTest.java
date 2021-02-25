package com.octo.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;

@RunWith(Cucumber.class)
@TestPropertySource(locations="classpath:test.properties")
@CucumberOptions(features = "src/test/resources",plugin = {"pretty","json:target/cucumber.json"})
public class CucumberRunnerTest {


}
