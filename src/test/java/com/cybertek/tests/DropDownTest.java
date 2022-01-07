package com.cybertek.tests;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DropDownTest {
    /*
    1.	Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
2.	Login with-----Username: Tester, password: test
    */
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        //implicitly wait, this is going to be applied the whole test cases and elements
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test(){
        WebElement userInputBox = driver.findElement(By.id("ctl00_MainContent_username"));
        userInputBox.sendKeys("Tester");

        WebElement passwordInputBox = driver.findElement(By.id("ctl00_MainContent_password"));
        passwordInputBox.sendKeys("test", Keys.ENTER);

        WebElement orderButton = driver.findElement(By.linkText("Order"));
        orderButton.click();

        String expectedSelectedOption = "MyMoney";
        WebElement productDropDownElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select productDropDown = new Select(productDropDownElement);
        String actualSelectedOption = productDropDown.getFirstSelectedOption().getText();
        Assert.assertEquals(actualSelectedOption,expectedSelectedOption,"First Option selected is not expected");


    }

}
