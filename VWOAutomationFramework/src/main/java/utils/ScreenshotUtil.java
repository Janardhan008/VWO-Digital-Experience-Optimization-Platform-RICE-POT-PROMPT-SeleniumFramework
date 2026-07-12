package utils;

import constants.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private ScreenshotUtil() {}

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            return "";
        }
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FrameworkConstants.DATE_FORMAT));
            String fileName = testName + "_" + timestamp + FrameworkConstants.SCREENSHOT_FORMAT;
            File screenshotDir = new File(FrameworkConstants.SCREENSHOT_DIR);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotDir, fileName);
            FileHandler.copy(srcFile, destFile);
            return destFile.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot for test: " + testName, e);
        }
    }

    public static byte[] getScreenshotBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
