package com.tests;

import com.pages.LoginPage;
import com.utils.ConfigurationReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests extends TestBaseReporter {
    @Test
    public void successLoginTest() {
        test = report.createTest(getTestName()).assignCategory("positive").info("Simple login test");
        context.driver.get(ConfigurationReader.get("base_url"));
        assertTrue(
                new LoginPage(context)
                        .loginAsStandardUser()
                        .getFooterText()
                        .contains("Sauce Labs")
        );
    }

    @Test
    public void emptyLoginTest() {
        test = report.createTest(getTestName()).assignCategory("negative");
        context.driver.get(ConfigurationReader.get("base_url"));
        assertEquals(
                "Epic sadface: Username is required",
                new LoginPage(context).incorrectLoginAs("","")
        );
        System.out.println("This is empty login test sout");
    }

    @Test
    public void blockedUserLoginTest() {
        test = report.createTest(getTestName()).assignCategory("negative");
        test.skip("Skipped because I wanted to");
        context.driver.get(ConfigurationReader.get("base_url"));
        assertEquals(
                "BLBALBLABLABLAB",
                new LoginPage(context).incorrectLoginAs("locked_out_user","secret_sauce")
        );
        test.info("Adding screenshot");

        test.addScreenCaptureFromBase64String(getScreenshot());
        //test.warning("This is warning!");
    }
}
