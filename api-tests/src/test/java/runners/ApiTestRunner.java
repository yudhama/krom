package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
public class ApiTestRunner extends AbstractTestNGCucumberTests {
    
    @BeforeClass
    public void setUp() {
        LogManager.info("Starting API Test Suite");
        ReportManager.initializeReport();
    }
    
    @AfterClass
    public void tearDown() {
        LogManager.info("Finishing API Test Suite");
        ReportManager.flushReport();
    }
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}