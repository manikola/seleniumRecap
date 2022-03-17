package com.herokuapp.theinternet.checkboxespagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends TestUtilities {

    @Test
    public void selectingTwoCheckboxesTest(){
        log.info("Starting selectingTwoCheckboxesTest");

        //open main page
        WelcomePage welcomePage = new WelcomePage(driver,log);
        welcomePage.openPage();

//        click on checkboxes link
        CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();

//        select all checkboxes
        checkboxesPage.selectAllCheckboxes();

//        verify all checkboxes are checked
        Assert.assertTrue(checkboxesPage.areAllCheckboxesChecked(), "Not all boxes are selected");
    }
}
