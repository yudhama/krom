package utils;

import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private static final Logger logger = Logger.getLogger(LogManager.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static void info(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info("[" + timestamp + "] " + message);
        System.out.println("[INFO] [" + timestamp + "] " + message);
    }
    
    public static void error(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.severe("[" + timestamp + "] " + message);
        System.err.println("[ERROR] [" + timestamp + "] " + message);
    }
    
    public static void debug(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.fine("[" + timestamp + "] " + message);
        System.out.println("[DEBUG] [" + timestamp + "] " + message);
    }
    
    public static void warn(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.warning("[" + timestamp + "] " + message);
        System.out.println("[WARN] [" + timestamp + "] " + message);
    }
    
    public static void apiRequest(String method, String endpoint, String payload) {
        info("API REQUEST: " + method + " " + endpoint);
        if (payload != null && !payload.isEmpty()) {
            debug("Request Payload: " + payload);
        }
    }
    
    public static void apiResponse(int statusCode, String responseBody) {
        info("API RESPONSE: Status Code " + statusCode);
        debug("Response Body: " + responseBody);
    }
    
    public static void testStart(String testName) {
        info("========== TEST STARTED: " + testName + " ==========");
    }
    
    public static void testEnd(String testName, String status) {
        info("========== TEST ENDED: " + testName + " - " + status + " ==========");
    }
}