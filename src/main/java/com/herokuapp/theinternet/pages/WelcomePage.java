package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject{

    private String pageUrl = "http://the-internet.herokuapp.com/";

    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");
    private By dropdownLinkLocator = By.linkText("Dropdown");
    private By jsAlertsLinkLocator = By.linkText("JavaScript Alerts");
    private By multipleWindowsLocator = By.linkText("Multiple Windows");
    private By editorLocator = By.linkText("WYSIWYG Editor");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openPage(){
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened");
    }

    public LoginPage clickFormAuthenticationLink(){
        log.info("Clicking FormAuthentication link on Welcome page");
        click(formAuthenticationLinkLocator);

        return new LoginPage(driver,log);
    }

    public CheckboxesPage clickCheckboxesLink(){
        log.info("Clicking Checkboxes link on Welcome page");
        click(checkboxesLinkLocator);

        return new CheckboxesPage(driver,log);
    }

    public DropdownPage clickDropdownLink(){
        log.info("Clicking Dropdown link on Welcome page");
        click(dropdownLinkLocator);

        return new DropdownPage(driver,log);
    }

    public AlertsPage clickJsAlertsLink(){
        log.info("Clicking JS Alerts on Welcome page");
        click(jsAlertsLinkLocator);

        return new AlertsPage(driver,log);
    }

    public WindowsPage clickMultipleWindowsLink(){
        log.info("Clicking Multiple Windows on Welcome page");
        click(multipleWindowsLocator);

        return new WindowsPage(driver,log);
    }

    public EditorPage clickEditorLink(){
        log.info("Clicking WYSIWYG Editor on Welcome page");
        click(editorLocator);

        return new EditorPage(driver,log);
    }
}
