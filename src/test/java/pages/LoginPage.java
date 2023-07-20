package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.Driver;

public class LoginPage {


    // Page Object Model is used for maintainability of the Webelements

    public WebElement emailAddress = Driver.getDriver().findElement(By.id("exampleInputEmail1"));

    public WebElement password = Driver.getDriver().findElement(By.id("exampleInputPassword1"));

    public WebElement SignIn = Driver.getDriver().findElement(By.xpath("//div/form/button"));

    public WebElement SignUp = Driver.getDriver().findElement(By.xpath("//div[2]/div/div[3]/a"));





    public void login(String username, String password){
        emailAddress.sendKeys(username, Keys.TAB, password, Keys.ENTER);
    }

}
