package com.intel471.pages;

import com.intel471.utils.BrowserUtils;
import com.intel471.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public final class HomePage extends BasePage{

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //================================= LOCATORS =================================

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


    //================================= METHODS =================================

    /**
     * searches the given product
     * adds the FIRST AVAILABLE product into Cart
     * it checks only the first page
     * @param product
     */
    public void searchAndAddTheFirstProductToCart(String product){
        try {
            //it clears the box -- just in case something written beforehand
            searchBox.clear();
            //enters the desired product name and clicks ENTER
            searchBox.sendKeys(product + Keys.ENTER);
            //verifies the title
            verifyTitle("Amazon.com : "+product);


            //clicks on first displayed product
            firstProduct.click();

            //if product is available "add to cart" button is displayed, if not "add to list" button is displayed)
            //waits for "add to cart" button to be displayed
            //if not --> it continues execution with catch block
            //if yes --> it continues execution with getting the productTitle and adding it to cart
            waitVisibilityOf(addToCartButton);

            //getting the product title
            String productTitleText = productTitle.getText();
            //clicking on "add to cart" button
            addToCartButton.click();
            waitVisibilityOf(addedToCartText);
            //asserting
            Assert.assertTrue(addedToCartText.isDisplayed());
            System.out.println(productTitleText + " -- has been added to Cart!");
        }catch (Exception e){
            //it will continue by trying to find next product which is AVAILABLE for shipping
            int i = 2;

            //One page has 28 products
            while (i<=28){
                try {
                    Driver.getDriver().navigate().back();
                    WebElement element = Driver.getDriver().findElement(By.xpath("//img[@data-image-index=\""+i+"\"]"));
                    waitVisibilityOf(element);
                    element.click();
                    //if it finds available element, loop gets terminated
                    //if it doesn't find available element, loop keeps going to find available product in the first page by iterating i by 1
                    //if it can't find available element in the 1st page, it will fail

                    waitVisibilityOf(addToCartButton);
                    String productTitleText = productTitle.getText();
                    addToCartButton.click();
                    waitVisibilityOf(addedToCartText);
                    Assert.assertTrue(addedToCartText.isDisplayed());
                    System.out.println(productTitleText + " -- has been added to Cart!");
                    break;
                }catch (RuntimeException ex){
                    i++;
                }
            }
            throw new RuntimeException("Available product can't be found in 1st page!");
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
