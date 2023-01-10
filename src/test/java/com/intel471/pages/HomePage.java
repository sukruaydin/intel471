package com.intel471.pages;

import com.intel471.utils.BrowserUtils;
import com.intel471.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public final class HomePage extends BasePage{

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(xpath = "//img[@data-image-index=\"1\"]")
    public WebElement firstProduct;

    @FindBy(xpath = "//img[@data-image-index=\"2\"]")
    public WebElement secondProduct;

    @FindBy(id = "productTitle")
    public WebElement productTitle;

    @FindBy(id = "add-to-wishlist-button-submit")
    public WebElement addToListButton;

    @FindBy(id = "add-to-cart-button")
    public WebElement addToCartButton;

    @FindBy(xpath = "//span[text()[normalize-space() =\"Added to Cart\"]]")
    public WebElement addedToCartText;

    @FindBy(xpath = "//span[text()[normalize-space() =\"Cart\"]]")
    public WebElement cartButton;

    @FindBy(xpath = "//span[@class=\"a-truncate-cut\"]")
    public List<WebElement> shoppingElements;

    @FindBy(xpath = "//input[@data-action=\"delete\"]")
    public List<WebElement> deleteButtons;



    public void searchAndAddTheFirstProductToCart(String product){
        try {
            searchBox.clear();
            searchBox.sendKeys(product + Keys.ENTER);
            verifyTitle("Amazon.com : "+product);
            firstProduct.click();
            waitVisibilityOf(addToCartButton);
            System.out.println(productTitle.getText() + " -- is added to cart!");
            addToCartButton.click();
            waitVisibilityOf(addedToCartText);
            Assert.assertTrue(addedToCartText.isDisplayed());
        }catch (Exception e){
            Driver.getDriver().navigate().back();
            secondProduct.click();
            waitVisibilityOf(addToCartButton);
            System.out.println(productTitle.getText() + " -- is added to cart!");
            addToCartButton.click();
            waitVisibilityOf(addedToCartText);
            Assert.assertTrue(addedToCartText.isDisplayed());
        }

    }

    public List<String> shoppingList(){
        List<String> list = new ArrayList<>();
        for (WebElement each : deleteButtons) {
            list.add(each.getAttribute("aria-label").substring(7));
        }
        return list;
    }


    public boolean isDeleted(String productName){
        BrowserUtils.sleep(2);
        List<String> list = shoppingList();
        return ! list.contains(productName);
    }

    /**
     * removes the first element in the cart
     * asserts if it is deleted successfully or not
     */
    public void removeAProductFromCart() {
        //waits for the cartButton-element to appear
        //click the element and verifies the title
        waitVisibilityOf(cartButton);
        cartButton.click();
        verifyTitle("Amazon.com Shopping Cart");

        //terminates the program id the cart is empty
        if (shoppingList().size()==0){
            throw new RuntimeException("The Cart is empty");
        }

        //waits for the first delete-button to appear
        waitVisibilityOf(deleteButtons.get(0));

        //gets the first element's name to be removed -- (for assertion later on)
        String toBeRemovedProductName = deleteButtons.get(0).getAttribute("aria-label").substring(7);

        //removes
        deleteButtons.get(0).click();

        //asserts if the given element is removed or not
        Assert.assertTrue(isDeleted(toBeRemovedProductName));
        System.out.println(toBeRemovedProductName + " -- has been removed from the Cart");
    }

    /**
     * removes all products from cart
     */
    public void removeAllProductsFromCart(){
        //waits for the cartButton-element to appear
        //click the element and verifies the title
        waitVisibilityOf(cartButton);
        cartButton.click();
        verifyTitle("Amazon.com Shopping Cart");

        if (shoppingList().size()==0){
            System.out.println("The Cart has been already empty:)");
            return;
        }

        while (true){
            try {
                removeAProductFromCart();
            }catch (RuntimeException e){
                System.out.println("The Cart has been emptied");
                return;
            }
        }
    }


}
