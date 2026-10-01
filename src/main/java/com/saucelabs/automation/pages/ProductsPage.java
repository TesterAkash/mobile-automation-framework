package com.saucelabs.automation.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class ProductsPage extends BasePage {

    private final By productsHeader = AppiumBy.accessibilityId("test-Cart drop zone");
    private final By menuButton = AppiumBy.accessibilityId("test-Menu");
    private final By logoutButton = AppiumBy.accessibilityId("test-LOGOUT");
    private final By firstAddToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");
    private final By cartButton = AppiumBy.accessibilityId("test-Cart");

    public boolean isProductsHeaderDisplayed() {
        return isElementDisplayed(productsHeader);
    }

    public void logout() {
        click(menuButton);
        click(logoutButton);
    }

    public void addFirstItemToCart() {
        click(firstAddToCartButton);
    }

    public void goToCart() {
        click(cartButton);
    }
}