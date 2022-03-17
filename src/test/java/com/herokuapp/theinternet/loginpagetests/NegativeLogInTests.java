package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class NegativeLogInTests extends TestUtilities {


    @Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {
//        data
        String no = testData.get("no");
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");



        WelcomePage welcomePageObject = new WelcomePage(driver, log);
        welcomePageObject.openPage();

        // Click on Form Authentication link
        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

        // enter username and password
        loginPage.negativeLogin(username, password);

        // wait for error message
        loginPage.waitForErrorMessage();
        String message = loginPage.getErrorMessageText();


        // Verification

        Assert.assertTrue(message.contains(expectedErrorMessage),
                "actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
                        + expectedErrorMessage + "\nactualErrorMessage: " + message);
    }


}
