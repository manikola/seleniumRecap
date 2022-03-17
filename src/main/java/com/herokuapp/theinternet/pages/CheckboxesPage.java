package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePageObject{


    private final By checkboxesLocator = By.xpath("//input[@id='checkbox']");


    public CheckboxesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectAllCheckboxes(){
        log.info("Checking all unchecked checkboxes");
        List<WebElement> checkboxes = findAll(checkboxesLocator);
        for(WebElement item : checkboxes){
            if(!item.isSelected()){
                item.click();
            }
        }
    }

    public boolean areAllCheckboxesChecked(){
        log.info("Verifying, that all checkboxes are checked");
            List<WebElement> checkboxes = findAll(checkboxesLocator);
            for(WebElement item : checkboxes){
                if(!item.isSelected()){
                    return false;
                }
        }
            return true;
    }
}
