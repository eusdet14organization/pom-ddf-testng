package com.pages;

import com.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends InternalPage {
    @CacheLookup // Opasno!
    @FindBy(css = ".btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(how = How.XPATH, using = "//input")
    public WebElement input;

    @FindBys({@FindBy(css = "#id"), @FindBy(xpath = "//input[@id='ad']")}) // AND
    public WebElement elementBys;

    @FindAll({@FindBy(css = "#id"), @FindBy(xpath = "//input[@id='ad']")}) // OR
    public List<WebElement> elementAll;

    public MainPage(TestContext context) {
        super(context);
    }

    public MainPage clickAllAddToCartButtons() {
        assertTrue(addToCartButtons.get(0).isEnabled(), "Add to cart button at main page wasn't enabled!");
        addToCartButtons.forEach(WebElement::click);
        return this;
    }
}
