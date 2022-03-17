package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject{

    private String pageUrl = "http://the-internet.herokuapp.com/secure";

    private By messageLocator = By.id("flash-messages");
    private By logoutLocator = By.xpath("//a[@class='button secondary radius']");

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getPageUrl(){
        return pageUrl;
    }

    public boolean isLogoutButtonVisible(){
       return  find(logoutLocator).isDisplayed();
    }

    public String getSuccessMessageText(){
       return find(messageLocator).getText();
    }


}
