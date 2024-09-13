package com.pages;

import com.context.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageTestNG {

    public BasePageTestNG(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
