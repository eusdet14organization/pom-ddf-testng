package com.tests;

import com.context.TestContext;
import com.utils.ConfigurationReader;
import com.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {
    TestContext context;

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
}
