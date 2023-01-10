package com.intel471.step_defs;


import com.intel471.pages.HomePage;
import com.intel471.pages.LoginPage;
import com.intel471.utils.ConfigurationReader;
import com.intel471.utils.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StepDefs {

    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();


    @Given("User navigates amazon.com")
    public void userNavigatesAmazonCom() {
        loginPage.navigateTo(ConfigurationReader.getProperty("url"));
    }

    @Then("User clicks sign in bar")
    public void userClicksSignInBar() {
        loginPage.waitVisibilityOf(loginPage.signInBar);
        loginPage.signInBar.click();
        loginPage.verifyTitle("Amazon Sign-In");
    }

    @And("User logs in")
    public void userLogsIn() {
        loginPage.login();
    }


    @And("User searches {string}, adds the first product to cart")
    public void userSearchesAddsTheFirstProductToCart(String product) {
        homePage.searchAndAddTheFirstProductToCart(product);
    }



    @And("User removes a product from cart")
    public void userRemovesAProductFromCart() {
        homePage.removeAProductFromCart();
    }

    @And("User removes all products from cart")
    public void userRemovesAllProductsFromCart() {
        homePage.removeAllProductsFromCart();
    }
}
