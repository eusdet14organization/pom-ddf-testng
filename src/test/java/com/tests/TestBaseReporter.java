package com.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.context.TestContext;
import com.utils.ConfigurationReader;
import com.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class TestBaseReporter {
    TestContext context;

    ExtentTest test;
    static ExtentReports report;
    static ExtentSparkReporter reporter;


    @BeforeAll
    public void beforeAllMethod() {
        reporter =  new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/" + this.getClass().getSimpleName() + ".html");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("report");
        reporter.config().setTheme(Theme.STANDARD);
        report = new ExtentReports();

        report.attachReporter(reporter);
    }

    @BeforeEach
    public void beforeMethod() {
        context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
        context.js = (JavascriptExecutor) context.driver;
        //context.driver.get(ConfigurationReader.get("base_url"));
    }

    @AfterEach
    public void afterMethod() {
        if (context.driver != null) {
            context.driver.quit();
        }
    }

    @AfterAll
    public static void afterAllMethod() {
        report.flush();
    }

    protected String getTestName() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        return this.getClass().getSimpleName() + "_" + stack[1].getMethodName();
    }

    protected String getScreenshot() {
        return ((TakesScreenshot)context.driver).getScreenshotAs(OutputType.BASE64);
    }

    @RegisterExtension
    private final AfterTestExecutionCallback afterTest = context -> {
        final Optional<Throwable> exception = context.getExecutionException();
        exception.ifPresentOrElse(this::onError, () -> onSuccess(context));
    };

    private void onSuccess(ExtensionContext context) {
        test.pass(context.getDisplayName() + "passed!");
    }

    private void onError(Throwable throwable1) {
        test.fail(throwable1.getMessage());
    }
}
