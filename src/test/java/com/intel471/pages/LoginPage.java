package com.intel471.pages;


import com.intel471.utils.ConfigurationReader;
import com.intel471.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public final class LoginPage extends BasePage{

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(id = "nav-link-accountList-nav-line-1")
    public WebElement signInBar;

    @FindBy(id = "ap_email")
    public WebElement emailBar;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "ap_password")
    public WebElement passwordBar;

    @FindBy(id = "signInSubmit")
    public WebElement signInButton;


    public void login(){
        emailBar.sendKeys(ConfigurationReader.getProperty("username"));
        continueButton.click();
        waitVisibilityOf(passwordBar);
        passwordBar.sendKeys(ConfigurationReader.getProperty("password"));
        signInButton.click();
        verifyTitle("Amazon.com. Spend less. Smile more.");
    }




}
