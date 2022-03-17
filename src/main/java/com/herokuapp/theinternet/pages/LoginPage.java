package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

    private String pageUrl = "http://the-internet.herokuapp.com/login";
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginLocator = By.xpath("//button[@class ='radius']");
    private By errorMsgLocator = By.id("flash");


    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public SecureAreaPage loginToSecureArea(String username, String password) {
        log.info("Executing login with username[" + username + "] and password[" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginLocator);

        return new SecureAreaPage(driver, log);
    }

    public void negativeLogin(String username, String password) {
        log.info("Executing login with username[" + username + "] and password[" + password + "]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginLocator);
    }
    public void waitForErrorMessage(){
        waitForVisibilityOf(errorMsgLocator,5);
    }

    public String getErrorMessageText(){
        return find(errorMsgLocator).getText();
    }


}
