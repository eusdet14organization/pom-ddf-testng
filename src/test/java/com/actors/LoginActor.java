package com.actors;

import com.context.TestContext;
import com.pages.plain.PlainLoginPage;
import com.utils.ConfigurationReader;

public class LoginActor {
    PlainLoginPage plp;
    TestContext context;

    public LoginActor(TestContext context){
        this.context = context;
        plp = new PlainLoginPage(context);
    }

    private void login(String username, String password) {
        plp.usernameInput.sendKeys(username);
        plp.passwordInput.sendKeys(password);
        plp.loginButton.click();
    }

    public MainPageActor loginAsStandardUser() {
        return loginAs(ConfigurationReader.get("standard_login"), ConfigurationReader.get("password"));
    }

    public MainPageActor loginAs(String username, String password) {
        login(username, password);
        return new MainPageActor(context);
    }

    public String incorrectLoginAs(String username, String password) {
        login(username, password);
        return plp.loginMessageContainer.getText();
    }
}
