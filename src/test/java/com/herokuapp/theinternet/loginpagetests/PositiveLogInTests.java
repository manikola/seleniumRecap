package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PositiveLogInTests extends TestUtilities {

    @Test
    public void logInTest() {

        // open main page
        WelcomePage welcomePageObject = new WelcomePage(driver,log);
        welcomePageObject.openPage();
        takeScreenshot("Welcome page opened");


        // Click on Form Authentication link
       LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

        // enter username and password
       SecureAreaPage secureAreaPage = loginPage.loginToSecureArea("tomsmith", "SuperSecretPassword!");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // verifications
        // new url

        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // log out button is visible
        Assert.assertTrue(secureAreaPage.isLogoutButtonVisible(), "logOutButton is not visible.");

        // Successful log in message
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
                "actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
                        + expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);
    }
}
