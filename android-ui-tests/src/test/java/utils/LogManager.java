package utils;

import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(LogManager.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info("[{}] {}", timestamp, message);
        System.out.println("[INFO] [" + timestamp + "] " + message);
    }
    
    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.error("[{}] {}", timestamp, message);
        System.err.println("[ERROR] [" + timestamp + "] " + message);
    }
    
    public static void debug(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.debug("[{}] {}", timestamp, message);
        System.out.println("[DEBUG] [" + timestamp + "] " + message);
    }
    
    public static void warn(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.warn("[{}] {}", timestamp, message);
        System.out.println("[WARN] [" + timestamp + "] " + message);
    }
    
    public static void stepInfo(String stepName, String description) {
        info("STEP: " + stepName + " - " + description);
    }
    
    public static void testStart(String testName) {
        info("========== TEST STARTED: " + testName + " ==========");
    }
    
    public static void testEnd(String testName, String status) {
        info("========== TEST ENDED: " + testName + " - " + status + " ==========");
    }
}