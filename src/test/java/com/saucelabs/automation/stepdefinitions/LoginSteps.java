package com.saucelabs.automation.stepdefinitions;

import com.saucelabs.automation.pages.CartPage;
import com.saucelabs.automation.pages.LoginPage;
import com.saucelabs.automation.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();

    @Given("I launch the Sauce Labs mobile application")
    public void i_launch_the_sauce_labs_mobile_application() {
        // Driver automatically launches app in Hooks @Before
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the product catalog screen")
    public void i_should_see_the_product_catalog_screen() {
        Assert.assertTrue(productsPage.isProductsHeaderDisplayed(), "Products header missing!");
    }

    @Then("I should see an error message containing {string}")
    public void i_should_see_an_error_message_containing(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
            "Expected error to contain '" + expectedError + "' but was '" + actualError + "'.");
    }

    @When("I open the menu and tap logout")
    public void i_open_the_menu_and_tap_logout() {
        productsPage.logout();
    }

    @Then("I should be navigated back to the login screen")
    public void i_should_be_navigated_back_to_the_login_screen() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Failed to log out to login screen!");
    }

    @When("I add the first product to the cart")
    public void i_add_the_first_product_to_the_cart() {
        productsPage.addFirstItemToCart();
    }

    @When("I navigate to the cart page")
    public void i_navigate_to_the_cart_page() {
        productsPage.goToCart();
    }

    @Then("I should see the item listed in the cart")
    public void i_should_see_the_item_listed_in_the_cart() {
        Assert.assertTrue(cartPage.isItemInCart(), "Added item was not found in cart!");
    }
}