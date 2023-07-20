package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.Driver;
import utils.SeleniumUtils;

import java.time.Duration;

public class LoginTests extends TestBase {

    @Test
    public void positiveLoginNonPageObkectModel(){
        SeleniumUtils.waitFor(2);
        new LoginPage().emailAddress.sendKeys(ConfigReader.getProperty("username"), Keys.TAB, ConfigReader.getProperty("password"), Keys.ENTER);
        SeleniumUtils.waitFor(5);
    }





}
