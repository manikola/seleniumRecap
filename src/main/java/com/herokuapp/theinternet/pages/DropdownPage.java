package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject{

    private By dropdownLocator = By.xpath("//select[@id='dropdown']");

    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectOption(int i){
       log.info("Selecting option " + i + " from dropdown");
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(i);

    }

    public String getSelectedOption(){
        WebElement dropdownElement = find(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        return selectedOption;
    }
}
