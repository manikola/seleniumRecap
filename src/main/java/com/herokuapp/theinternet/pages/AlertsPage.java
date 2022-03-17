package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage extends BasePageObject{

    private final By jsAlertLocator = By.xpath("//button[@onclick='jsAlert()']");
    private final By jsConfirmLocator = By.xpath("//button[@onclick='jsConfirm()']");
    private final By jsPromptLocator = By.xpath("//button[@onclick='jsPrompt()']");
    private final By resultTextLocator = By.id("result");

    public AlertsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void clickJsAlert(){
        log.info("Clicking on 'JSAlert' button to open alert");
        click(jsAlertLocator);
    }
    public void clickJsConfirm(){
        log.info("Clicking on 'JSConfirm' button to open alert");
        click(jsConfirmLocator);
    }
    public void clickJsPrompt(){
        log.info("Clicking on 'JSPrompt' button to open alert");
        click(jsPromptLocator);
    }

    public String getAlertText(){
        String text = switchToAlert().getText();
        log.info("Alert says: " + text);
        return text;
    }

    public void acceptAlert(){
        log.info("Switching to alert and pressing ok");
        Alert alert = switchToAlert();
        alert.accept();
    }

    public void dismissAlert(){
        log.info("Switching to alert and pressing cancel");
        Alert alert = switchToAlert();
        alert.dismiss();
    }

    public void typeTextIntoAlert(String text){
        log.info("Typing text into alert and pressing ok");
        Alert alert = switchToAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    public String getResultText(){
        String result = find(resultTextLocator).getText();
        log.info("Result text: " + result);
        return result;
    }
}
