package com.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utils.ConfigurationReader;
import com.utils.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

public class TestBaseTestNG {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    // This class is used to start and build reports
    protected ExtentReports report;

    // This class is used to create HTML report file
    protected ExtentSparkReporter htmlReporter;

    // This class defines a test, enables adding logs, authors, test steps
    protected ExtentTest extentLogger;

    @BeforeTest
    public void setupTest() {

        // Intialize the class
        report = new ExtentReports();

        // Create a report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";

        // Initialize the HTML reporter with the report path
        htmlReporter = new ExtentSparkReporter(path);

        // Attach the HTML report to the report object
        report.attachReporter(htmlReporter);

        // Title in Report
        htmlReporter.config().setReportName("EuroTech Login Test");

        // Set environment information
        report.setSystemInfo("Environment", "Test");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS", System.getProperty("os name"));

    }

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.get();
        //   driver.manage().window().maximize();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, IOException, IOException {

        // If test fails
        if(result.getStatus()== ITestResult.FAILURE) {

            // Record the name of failed test
            extentLogger.fail(result.getName());

            // Add the screenshot to the report
            extentLogger.addScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64));

            // Capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());
        } else {
            extentLogger.pass(result.getName());
        }

        Thread.sleep(2000);
        driver.close();

    }

    @AfterTest
    public void tearDownTest() {

        // This is when the report is created
        report.flush();

    }


}
