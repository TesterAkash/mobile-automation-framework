package com.saucelabs.automation.driver;

import com.saucelabs.automation.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    public static AppiumDriver createDriver(String platformName) throws Exception {
        String appiumServer = ConfigReader.get("appium.server.url", "http://127.0.0.1:4723");
        URL appiumServerUrl = URI.create(appiumServer).toURL();

        if (platformName.equalsIgnoreCase("android")) {
            String appPath = resolveAppPath(ConfigReader.get("app.path", "apps/sample-app-android.apk"));
            String deviceName = System.getProperty("android.device", ConfigReader.get("device.name", "Android Device"));
            String platformVersion = System.getProperty("android.version", ConfigReader.get("platform.version", "9"));
            String deviceUdid = System.getProperty("android.udid", ConfigReader.get("device.udid", ""));

            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName(deviceName)
                    .setPlatformVersion(platformVersion)
                    .setApp(appPath)
                    .setAppPackage("com.swaglabsmobileapp")
                    .setAppActivity("com.swaglabsmobileapp.SplashActivity")
                    .setAppWaitActivity("com.swaglabsmobileapp.*")
                    .setAutoGrantPermissions(Boolean.parseBoolean(ConfigReader.get("autoGrantPermissions", "true")))
                    .setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("new.command.timeout", "240"))));

            if (!deviceUdid.isBlank()) {
                options.setUdid(deviceUdid);
            }
            options.setIgnoreHiddenApiPolicyError(true);
            options.setSkipDeviceInitialization(true);

            return new AndroidDriver(appiumServerUrl, options);
        } else if (platformName.equalsIgnoreCase("ios")) {
            String appPath = resolveAppPath(ConfigReader.get("app.path", "apps/sample-app-ios.ipa"));
            XCUITestOptions options = new XCUITestOptions()
                    .setPlatformName("iOS")
                    .setDeviceName(System.getProperty("ios.device", ConfigReader.get("device.name", "iPhone 14")))
                    .setPlatformVersion(System.getProperty("ios.version", ConfigReader.get("platform.version", "16.0")))
                    .setApp(appPath)
                    .setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(ConfigReader.get("new.command.timeout", "240"))));

            return new IOSDriver(appiumServerUrl, options);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }
    }

    private static String resolveAppPath(String appPath) {
        if (appPath.startsWith("/") || appPath.matches("[A-Za-z]:\\.*")) {
            return appPath;
        }
        return System.getProperty("user.dir") + "/" + appPath;
    }
}