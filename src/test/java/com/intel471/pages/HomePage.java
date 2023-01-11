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

    //search-box located on top of the screen
    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    //first product displayed on the page
    @FindBy(xpath = "//img[@data-image-index=\"1\"]")
    public WebElement firstProduct;

    //product name
    @FindBy(id = "productTitle")
    public WebElement productTitle;

    //Add to Cart button located on right side of the screen
    @FindBy(id = "add-to-cart-button")
    public WebElement addToCartButton;

    //"Added to Cart" text, appeared after clicking "Add to Cart" button
    @FindBy(xpath = "//span[text()[normalize-space() =\"Added to Cart\"]]")
    public WebElement addedToCartText;

    //cart button, navigates into Cart, located top-right
    @FindBy(xpath = "//span[text()[normalize-space() =\"Cart\"]]")
    public WebElement cartButton;

    //all the delete buttons in the Cart
    //it is used for removing products
    //it is also used for getting all the listed products in the Cart
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

            //if product is available, "add to cart" button is displayed, if not "add to list" button is displayed
            //waits for "add to cart" button to be displayed
            //if not displayed --> it continues execution with catch block
            //if displayed --> it continues execution with getting the productTitle and adding it to cart
            waitVisibilityOf(addToCartButton);

            //adds the available product to Cart
            addAvailableProductToCart();
        }catch (Exception e){
            //first product is not AVAILABLE
            //it will continue by trying to find next product, which is AVAILABLE for shipping
            int i = 2;

            //One page has 28 products
            while (i<=28){
                try {
                    //navigates back to reach the search result
                    Driver.getDriver().navigate().back();

                    //dynamically locates the elements starting from element number 2 to 28.
                    WebElement element = Driver.getDriver().findElement(By.xpath("//img[@data-image-index=\""+i+"\"]"));
                    waitVisibilityOf(element);
                    element.click();

                    //if it finds available element, loop gets terminated by "break" statement below
                    //if it doesn't find available element, loop keeps going to find available product in the first page by iterating i by 1
                    //if it can't find available element in the 1st page, it will throw RuntimeException
                    waitVisibilityOf(addToCartButton);

                    //adds the available product to Cart
                    addAvailableProductToCart();
                    break;
                }catch (RuntimeException ex){
                    i++;
                }
            }
            throw new RuntimeException("Available product can't be found in 1st page!");
        }
    }


    /**
     * adds the previously confirmed available product to Cart
     */
    public void addAvailableProductToCart(){
        //getting the product title
        String productTitleText = productTitle.getText();
        //clicking on "add to cart" button
        addToCartButton.click();
        waitVisibilityOf(addedToCartText);
        //asserting
        Assert.assertTrue(addedToCartText.isDisplayed());
        System.out.println(productTitleText + " -- has been added to Cart!");
    }


    /**
     * returns all the current products in the Cart as List
     * @return
     */
    public List<String> shoppingList(){
        List<String> list = new ArrayList<>();
        for (WebElement each : deleteButtons) {
            list.add(each.getAttribute("aria-label").substring(7));
        }
        return list;
    }


    /**
     * checks given product is deleted from cart or not, returns boolean
     * @param productName
     * @return
     */
    public boolean isDeleted(String productName){
        BrowserUtils.sleep(2);

        //takes current product list from Cart
        List<String> list = shoppingList();

        //checks given product name is contained or not
        return ! list.contains(productName);
    }


    /**
     * removes the first product in the cart
     * asserts if it is deleted successfully or not
     */
    public void removeAProductFromCart() {
        //waits for the cartButton-element to appear
        //click the element and verifies the title
        waitVisibilityOf(cartButton);
        cartButton.click();
        verifyTitle("Amazon.com Shopping Cart");

        //terminates the program, if the cart is already empty
        if (shoppingList().size()==0){
            throw new RuntimeException("The Cart is empty");
        }

        //waits for the first delete-button to appear
        waitVisibilityOf(deleteButtons.get(0));

        //gets the first element's name to be removed -- (for asserting later on)
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

        //if Cart is empty, program gets terminated
        if (shoppingList().size()==0){
            System.out.println("The Cart has been already empty:)");
            return;
        }

        while (true){
            try {
                //it starts to empty the Cart
                //when it gets emptied, it throws RuntimeException (coming from removeAProductFromCart() method)
                //catch block will handle it.
                removeAProductFromCart();
            }catch (RuntimeException e){
                System.out.println("The Cart has been emptied");
                return;
            }
        }
    }


}
