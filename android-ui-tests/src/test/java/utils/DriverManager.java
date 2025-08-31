package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static AppiumDriver driver;
    private static final String APPIUM_SERVER_URL = "http://localhost:4723";
    
    public static void initializeDriver() {
        if (driver != null) {
            return; // Prevent double initialization
        }
        
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            
            // App capabilities
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "15.0");
            caps.setCapability("deviceName", "7DDQLZPVWGXOUWS8");
            caps.setCapability("automationName", "UiAutomator2");
            
            // Updated APK path
            caps.setCapability("app", "/Users/yudhamaulanaahmad/Downloads/mda-2.2.0-25.apk");
            
            // Additional capabilities
            caps.setCapability("newCommandTimeout", 300);
            caps.setCapability("connectHardwareKeyboard", true);
            caps.setCapability("noReset", true); // Don't reset app state
            caps.setCapability("fullReset", false);
            caps.setCapability("autoGrantPermissions", true); // Auto grant permissions
            
            driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver: " + e.getMessage());
        }
    }
    
    public static AppiumDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}