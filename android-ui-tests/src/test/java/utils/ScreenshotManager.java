package utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotManager {
    private static final String SCREENSHOT_DIR = "target/screenshots";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    
    static {
        createScreenshotDirectory();
    }
    
    private static void createScreenshotDirectory() {
        File directory = new File(SCREENSHOT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    public static String captureScreenshot(AppiumDriver driver, String testName) {
        try {
            String timestamp = LocalDateTime.now().format(formatter);
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + "/" + fileName;
            
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            
            FileUtils.copyFile(sourceFile, destFile);
            
            LogManager.info("Screenshot captured: " + filePath);
            return filePath;
        } catch (IOException e) {
            LogManager.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
    
    public static String captureScreenshotOnFailure(AppiumDriver driver, String testName) {
        String filePath = captureScreenshot(driver, testName + "_FAILED");
        if (filePath != null) {
            LogManager.error("Test failed - Screenshot saved: " + filePath);
        }
        return filePath;
    }
}