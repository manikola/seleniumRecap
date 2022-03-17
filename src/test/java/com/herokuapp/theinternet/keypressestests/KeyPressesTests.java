package com.herokuapp.theinternet.keypressestests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.KeyPressesPage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeyPressesTests extends TestUtilities {

    @Test
    public void pressKeyTest(){
        log.info("Starting pressKeyTest");

        // open KeyPressesPage
        KeyPressesPage keyPresses = new KeyPressesPage(driver, log);
        keyPresses.openPage();


        // Push keyboard key
        keyPresses.pressKey(Keys.ENTER);



        // Get Results text
        String result = keyPresses.getResultText();


        // Verify Result text is expected
        Assert.assertTrue(result.equals("You entered: ENTER"),
                "result is not expected. \nShould be 'You entered: ENTER', but it is '" + result + "'");
    }

    @Test
    public void pressKeyWithActionsTest() {
        log.info("Starting pressKeyWithActionsTest");

        // open KeyPressesPage
        KeyPressesPage keyPresses = new KeyPressesPage(driver, log);
        keyPresses.openPage();


        // Push keyboard key
        keyPresses.pressKeyWithActions(Keys.SPACE);


        // Get Results text
        String result = keyPresses.getResultText();


        // Verify Result text is expected
        Assert.assertTrue(result.equals("You entered: SPACE"),
                "result is not expected. \nShould be 'You entered: ENTER', but it is '" + result + "'");
    }

}
