package utils;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + FrameworkConstants.CONFIG_FILE_PATH, e);
        }
    }

    private ConfigReader() {}

    public static String getValue(String key) {
        String value = properties.getProperty(key);
        if (Objects.isNull(value)) {
            throw new RuntimeException("Property '" + key + "' not found in config file");
        }
        return value.trim();
    }

    public static String getAppUrl() {
        return getValue("app.url");
    }

    public static String getBrowser() {
        return getValue("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getValue("headless"));
    }

    public static String getUsername() {
        return getValue("app.username");
    }

    public static String getPassword() {
        return getValue("app.password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getValue("implicit.wait.seconds"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getValue("explicit.wait.seconds"));
    }

    public static boolean isRemoteExecution() {
        return Boolean.parseBoolean(getValue("remote.execution"));
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getValue("screenshot.on.failure"));
    }
}
