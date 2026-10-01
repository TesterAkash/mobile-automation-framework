package com.saucelabs.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_DIR = "src/test/resources/config";
    private static final Properties properties = new Properties();

    static {
        loadPlatformProperties();
    }

    private static void loadPlatformProperties() {
        String platform = System.getProperty("platform", "android");
        String configFile = platform.toLowerCase() + ".properties";
        Path configPath = Path.of(System.getProperty("user.dir"), CONFIG_DIR, configFile);

        try (InputStream inputStream = Files.newInputStream(configPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load config file: " + configPath, e);
        }

        properties.putIfAbsent("platform", platform);
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}

