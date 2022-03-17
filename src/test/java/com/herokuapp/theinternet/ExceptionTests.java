package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ExceptionTests {

    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser) {
//		Create driver
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Do not know how to start " + browser + ", starting chrome instead");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }


        // maximize browser window
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void notVisibleTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        WebElement finishElement = driver.findElement(By.id("finish"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(finishElement));
        String actualMsg = finishElement.getText();


        Assert.assertTrue(actualMsg.contains("Hello World!"));
    }

    @Test(priority = 2)
    public void timeOutTest() {
// open the page http://the-internet.herokuapp.com/dynamic_loading/1
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");

        // Find locator for startButton and click on it
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        // Then get finish element text
        WebElement finishElement = driver.findElement(By.id("finish"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(finishElement));
        } catch (TimeoutException exception) {
            System.out.println("Exception catched: " + exception.getMessage());

        }

        String finishText = finishElement.getText();

        // compare actual finish element text with expected "Hello World!" using Test NG
        // Assert class
        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text: " + finishText);

    }

    @Test(priority = 3)
    public void noSuchElementTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));


        String actualMsg = finishElement.getText();
        Assert.assertTrue(actualMsg.contains("Hello World!"));
    }

    @Test
    public void staleElementTest() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement checkBox = driver.findElement(By.id("checkbox"));
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        removeButton.click();


        Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkBox)),"Checkbox is still visible, but it shouldn't be");

        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
        addButton.click();

        checkBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
        Assert.assertTrue(checkBox.isDisplayed(),"Checkbox is not visible, but it should be");


    }

    @Test
    public void disabledElementTest(){
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
        WebElement inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));
        enableButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        inputField.sendKeys("successssss");

        Assert.assertEquals(inputField.getAttribute("value"),"successssss");
    }


    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        // Close browser
        driver.quit();
    }
}
