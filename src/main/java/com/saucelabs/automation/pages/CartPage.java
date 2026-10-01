package com.saucelabs.automation.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By cartItem = AppiumBy.accessibilityId("test-Item");

    public boolean isItemInCart() {
        return isElementDisplayed(cartItem);
    }
}