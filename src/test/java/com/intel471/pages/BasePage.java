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

    @FindBy(id = "nav-bb-logo")
    public WebElement amazonLogoNotWanted;

    @FindBy(id = "nav-logo-sprites")
    public WebElement amazonLogoWanted;

    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(),15);;


    public void navigateTo(String url){
        Driver.getDriver().get(url);
        try {
            amazonLogoWanted.isDisplayed();
        }catch (Exception e){
            amazonLogoNotWanted.click();
        }
    }


    public void verifyTitle(String expectedTitle){
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        Assert.assertEquals(expectedTitle,Driver.getDriver().getTitle());
    }


    public void waitVisibilityOf(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
