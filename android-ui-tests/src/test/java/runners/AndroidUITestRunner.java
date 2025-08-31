package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import utils.DriverManager;
import utils.ReportManager;
import utils.LogManager;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/html",
        "json:target/cucumber-reports/json/Cucumber.json",
        "junit:target/cucumber-reports/junit/Cucumber.xml"
    },
    monochrome = true
)
public class AndroidUITestRunner extends AbstractTestNGCucumberTests {
    
    @BeforeClass
    public void setUp() {
        LogManager.info("Starting Android UI Test Suite");
        ReportManager.initializeReport();
    }
    
    @AfterClass
    public void tearDown() {
        LogManager.info("Finishing Android UI Test Suite");
        DriverManager.quitDriver();
        ReportManager.flushReport();
    }
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}