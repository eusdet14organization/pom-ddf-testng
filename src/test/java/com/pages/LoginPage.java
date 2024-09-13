package com.pages;

import com.context.TestContext;
import com.utils.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = ".error-message-container")
    public WebElement loginMessageContainer;

    @FindBy(css = "#user-name")
    public WebElement usernameInput;

    @FindBy(css = "#password")
    public WebElement passwordInput;

    @FindBy(css = "#login-button")
    public WebElement loginButton;

    public LoginPage(TestContext context){
        super(context);
    }

    private void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public MainPage loginAsStandardUser() {
        return loginAs(ConfigurationReader.get("standard_login"), ConfigurationReader.get("password"));
    }

    public MainPage loginAs(String username, String password) {
        login(username, password);
        return new MainPage(context);
    }

    public String incorrectLoginAs(String username, String password) {
        login(username, password);
        return loginMessageContainer.getText();
    }
}
