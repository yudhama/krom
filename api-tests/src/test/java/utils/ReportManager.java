package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String REPORT_PATH = "target/reports/ExtentReport.html";
    
    public static void initializeReport() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("API Test Report");
            sparkReporter.config().setReportName("API Automation Test Results");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Platform", "API");
            extent.setSystemInfo("Framework", "REST Assured + Cucumber + TestNG");
            extent.setSystemInfo("Execution Date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
    
    public static void createTest(String testName, String description) {
        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
    }
    
    public static void logInfo(String message) {
        test.get().log(Status.INFO, message);
    }
    
    public static void logPass(String message) {
        test.get().log(Status.PASS, message);
    }
    
    public static void logFail(String message) {
        test.get().log(Status.FAIL, message);
    }
    
    public static void logApiDetails(String method, String endpoint, int statusCode, String responseTime) {
        test.get().log(Status.INFO, "API Call: " + method + " " + endpoint);
        test.get().log(Status.INFO, "Status Code: " + statusCode);
        test.get().log(Status.INFO, "Response Time: " + responseTime + "ms");
    }
    
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}