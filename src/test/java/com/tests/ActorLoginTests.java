package com.tests;

import com.actors.LoginActor;
import com.pages.LoginPage;
import com.pages.MainPageComponent;
import com.utils.ConfigurationReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorLoginTests extends TestBase {
    @Test
    public void successLoginTest() {
        context.driver.get(ConfigurationReader.get("base_url"));
        assertTrue(
            new LoginActor(context)
                .loginAsStandardUser()
                .getFooterText()
                .contains("Sauce Labs")
        );
    }

    @Test
    public void successLoginInternalAssertTest() {
        context.driver.get(ConfigurationReader.get("base_url"));
        new LoginActor(context)
                .loginAsStandardUser()
                .assertFooterTextContains("Sauce Labsssss");

        new MainPageComponent(context).internalPage.openCart();
    }
}
