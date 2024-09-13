package com.tests;

import com.pages.LoginPageTestNG;
import com.utils.ConfigurationReader;
import com.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDFTestNG extends TestBaseTestNG {
    @DataProvider
    public Object [][] userData(){
        ExcelUtil testData = new ExcelUtil("src/main/resources/com/tests/src/resources/login_test.xls","Sheet1");
        return testData.getDataArrayWithoutFirstRow();
    }

    @Test(dataProvider = "userData")
    public void ddfLoginTest(String username,String password,String errorMessage,String testName){
        extentLogger = report.createTest(testName);
        driver.get(ConfigurationReader.get("base_url"));
        Assert.assertEquals(new LoginPageTestNG(driver).incorrectLoginAs(username, password), errorMessage);
    }
}
