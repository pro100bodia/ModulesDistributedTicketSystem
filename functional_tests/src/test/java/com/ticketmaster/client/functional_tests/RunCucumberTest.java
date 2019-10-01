package com.ticketmaster.client.functional_tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/bdd/get_all_users.feature", glue = "com.ticketmaster.functional_tests")
public class RunCucumberTest {
}
