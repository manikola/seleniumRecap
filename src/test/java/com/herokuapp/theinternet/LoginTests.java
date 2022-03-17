package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser){
        switch(browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Unknown driver: " + browser + ", starting chrome by default");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }


        driver.manage().window().maximize();

    }

    @Test(priority = 1, groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest(){


//        open testpage
        driver.get("http://the-internet.herokuapp.com/login");

//        enter username
        WebElement userNameBox= driver.findElement(By.id("username"));
        userNameBox.sendKeys("tomsmith");

//        enter pw
        WebElement pwBox = driver.findElement(By.id("password"));
        pwBox.sendKeys("SuperSecretPassword!");

//        click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("secure"));

//        verifications
//        new url
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl, "Actual url is different then expected");

//        logout button visible
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(),"Logout button is not visible");

//        successful login msg
        WebElement successMessage= driver.findElement(By.id("flash"));
        String expectedMsg = "You logged into a secure area!";
        String actualMsg = successMessage.getText();

        Assert.assertTrue(actualMsg.contains(expectedMsg),"Actual message is different then expected");


    }

    @Parameters({"username", "password","expectedMessage"})
    @Test(priority = 2, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String userName, String pw,String expectedMsg){


//        open testpage
        driver.get("http://the-internet.herokuapp.com/login");

//        enter username
        WebElement userNameBox= driver.findElement(By.id("username"));
        userNameBox.sendKeys(userName);

//        enter pw
        WebElement pwBox = driver.findElement(By.id("password"));
        pwBox.sendKeys(pw);

//        click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();


//        verifications
//        new url
        String expectedUrl = "http://the-internet.herokuapp.com/login";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl, "Actual url is different then expected");


//        error msg
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String actualMsg = errorMessage.getText();

        Assert.assertTrue(actualMsg.contains(expectedMsg),"Actual message is different then expected");


    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }


}
