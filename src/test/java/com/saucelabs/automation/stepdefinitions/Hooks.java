package com.saucelabs.automation.stepdefinitions;

import com.saucelabs.automation.driver.DriverFactory;
import com.saucelabs.automation.driver.DriverManager;
import com.saucelabs.automation.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setUp() throws Exception {
        String platform = System.getProperty("platform", ConfigReader.get("platform", "android"));
        DriverManager.setDriver(DriverFactory.createDriver(platform));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        DriverManager.quitDriver();
    }
}