package com.intel471.pages;

import com.intel471.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    //================================= LOCATORS =================================

    /*
          when navigated to amazon, we encounter 2 website version
            WebElement amazonLogoWanted --> belongs to version 1 (mostly encountered)
            WebElement amazonLogoNotWanted --> belongs to version 2 (mostly NOT encountered)
     */

    //
    @FindBy(id = "nav-logo-sprites")
    public WebElement amazonLogoWanted;

    //
    @FindBy(id = "nav-bb-logo")
    public WebElement amazonLogoNotWanted;


    //================================= METHODS =================================


    //explicitly wait object
    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);;


    /**
     * navigates to given website (amazon.com)
     * when navigated to amazon, we encounter 2 website version
     * this method handles 2 version of those by try catch blocks
     * id it is not amazon website (for further use), it just prints a message on console
     * @param url
     */
    public void navigateTo(String url){
        Driver.getDriver().get(url);
        try {
            amazonLogoWanted.isDisplayed();
        }catch (RuntimeException e){
            amazonLogoNotWanted.click();
        }catch (Exception e){
            System.out.println("It is not amazon website");
        }
    }


    /**
     * first (explicitly) waits for title containing the given title
     * then, compares the actual title with the given title
     * @param expectedTitle
     */
    public void verifyTitle(String expectedTitle){
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        Assert.assertEquals(expectedTitle,Driver.getDriver().getTitle());
    }


    /**
     * (explicitly) waits for the visibility of given WebElement
     * @param element
     */
    public void waitVisibilityOf(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
