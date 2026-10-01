package com.saucelabs.automation.runners;

import org.testng.IExecutionListener;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CucumberReportListener implements IExecutionListener {

    @Override
    public void onExecutionFinish() {
        Path report = Path.of("target", "cucumber-reports.html").toAbsolutePath();
        System.out.println("Cucumber HTML report: " + report);

        if (!Files.isRegularFile(report)) {
            System.err.println("Cucumber HTML report was not found.");
            return;
        }

        if (!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            System.out.println("Open the report manually; desktop browsing is unavailable.");
            return;
        }

        try {
            Desktop.getDesktop().browse(report.toUri());
        } catch (IOException e) {
            System.err.println("Could not open the Cucumber HTML report: " + e.getMessage());
        }
    }
}