package com.tests;

import com.pages.LoginPage;
import com.utils.ConfigurationReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTests extends TestBase {
    @Test
    public void testAddItemsToCart() {
        context.driver.get(ConfigurationReader.get("base_url"));
        assertEquals(
                6,
                new LoginPage(context)
                        .loginAsStandardUser()
                        .clickAllAddToCartButtons()
                        .getShoppingCartItemsAmount()
        );
    }

    @Test
    public void testAddItemsToCartViaElement() {
        context.driver.get(ConfigurationReader.get("base_url"));
        assertEquals(
                "6",
                new LoginPage(context)
                        .loginAsStandardUser()
                        .clickAllAddToCartButtons()
                        .shoppingCartLink.getText()
        );
    }
}
