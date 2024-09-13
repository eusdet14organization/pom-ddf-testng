package com.pages;

import com.context.TestContext;

public class MainPageComponent extends BasePage {
    public InternalPage internalPage;
    public ShoppingCartPage shoppingCartPage;

    public MainPageComponent(TestContext context){
        super(context);
        internalPage = new InternalPage(context);
    }
}
