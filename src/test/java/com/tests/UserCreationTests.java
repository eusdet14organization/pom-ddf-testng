package com.tests;

import com.context.NewUserInfo;
import com.pages.UserCreationPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCreationTests extends TestBase {
    @Test
    public void createUserAllParams() throws InterruptedException {
        context.driver.get("https://demo.wpeverest.com/user-registration/online-event-registration-form/");
        new UserCreationPage(context).createNewUser(
                "Test",
                "User",
                "test" + System. currentTimeMillis() + "@mail.com",
                "PA$$w0rd_absolutely_Strong",
                "48516523779",
                "Male",
                "no need",
                false,
                "Social Media",
                "no need"
        );
        assertEquals("User successfully registered.", new UserCreationPage(context).successMessage.getText());
    }

    @Test
    public void createUserObject() throws InterruptedException {
        context.driver.get("https://demo.wpeverest.com/user-registration/online-event-registration-form/");
        NewUserInfo userInfo = new NewUserInfo();
        userInfo.heardBy = "Social Media";
        new UserCreationPage(context).createNewUser(userInfo);
        assertEquals("User successfully registered.", new UserCreationPage(context).successMessage.getText());
    }
}
