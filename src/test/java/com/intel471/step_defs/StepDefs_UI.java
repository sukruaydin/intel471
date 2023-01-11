package com.intel471.step_defs;


import com.intel471.pages.HomePage;
import com.intel471.pages.LoginPage;
import com.intel471.utils.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class StepDefs_UI {

    private LoginPage loginPage;


    @Given("User navigates amazon.com")
    public void userNavigatesAmazonCom() {
        //navigates to the given url
        new LoginPage().navigateTo(ConfigurationReader.getProperty("url"));
    }

    @Then("User clicks sign in bar")
    public void userClicksSignInBar() {
        loginPage = new LoginPage();

        //waits for the visibility of signInBar
        loginPage.waitVisibilityOf(loginPage.signInBar);

        //click on singInBar
        loginPage.signInBar.click();

        //title verification after clicking
        loginPage.verifyTitle("Amazon Sign-In");
    }

    @And("User logs in")
    public void userLogsIn() {
        //logging-in
        new LoginPage().login();
    }

    @And("User searches {string}, adds the first product to cart")
    public void userSearchesAddsTheFirstProductToCart(String product) {
        //searches and adds the first available product to cart
        new HomePage().searchAndAddTheFirstProductToCart(product);
    }

    @And("User removes a product from cart")
    public void userRemovesAProductFromCart() {
        //removes first product from Cart
        new HomePage().removeAProductFromCart();
    }

    @And("User removes all products from cart")
    public void userRemovesAllProductsFromCart() {
        //empties the Cart
        new HomePage().removeAllProductsFromCart();
    }


}
