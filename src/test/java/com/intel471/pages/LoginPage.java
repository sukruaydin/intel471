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

    //================================= LOCATORS =================================

    //WebElement located top-right corner
    //After clicking this bar, it navigates us to sign-in page
    @FindBy(id = "nav-link-accountList-nav-line-1")
    public WebElement signInBar;

    //email input box
    @FindBy(id = "ap_email")
    public WebElement emailBar;

    //continue button
    @FindBy(id = "continue")
    public WebElement continueButton;

    //password input box
    @FindBy(id = "ap_password")
    public WebElement passwordBar;

    //sign-in button
    @FindBy(id = "signInSubmit")
    public WebElement signInButton;

    //================================= METHODS =================================

    /**
     * reads username&password from configuration.properties file and logs-in
     * verifies title after logging-in
     */
    public void login(){
        //providing username and clicking continue
        emailBar.sendKeys(ConfigurationReader.getProperty("username"));
        continueButton.click();

        //waiting for password bar to come to screen
        waitVisibilityOf(passwordBar);

        //providing password and clicking sign-in
        passwordBar.sendKeys(ConfigurationReader.getProperty("password"));
        signInButton.click();

        //title verification after clicking
        verifyTitle("Amazon.com. Spend less. Smile more.");
    }




}
