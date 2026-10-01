package com.saucelabs.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(CucumberReportListener.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.saucelabs.automation.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "not @wip"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}