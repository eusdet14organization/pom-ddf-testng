package com.tests;

import com.pages.LoginPage;
import com.utils.ConfigurationReader;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginDDFTest extends TestBaseReporter {
    @ParameterizedTest
    @CsvFileSource(resources = "src/resources/login_test.csv", numLinesToSkip = 1)
    public void loginTest(String username, String password, String errorMessage, String testName) {
        test = report.createTest(testName);
        context.driver.get(ConfigurationReader.get("base_url"));
        assertEquals(
                errorMessage,
                new LoginPage(context).incorrectLoginAs(username,password)
        );
    }
}
