package com.pages;

import com.context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InternalPage extends BasePage{
    @FindBy(css = ".footer_copy")
    public WebElement footer;

    @FindBy(css = ".shopping_cart_link")
    public WebElement shoppingCartLink;

    protected InternalPage(TestContext context) {
        super(context);
    }

    public String getFooterText() {
        return footer.getText();
    }

    public ShoppingCartPage openCart() {
        shoppingCartLink.click();
        return new ShoppingCartPage();
    }

    public int getShoppingCartItemsAmount() {
        return shoppingCartLink.getText().length() > 0 ? Integer.parseInt(shoppingCartLink.getText()) : 0;
    }
}
