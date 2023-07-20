package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.Driver;

public class SignUpPage {

    public WebElement firstname = Driver.getDriver().findElement(By.id("inputfirstname4"));
    public WebElement lastname = Driver.getDriver().findElement(By.id("inputlastname4"));

    public WebElement email = Driver.getDriver().findElement(By.id("email"));

    public WebElement password2 = Driver.getDriver().findElement(By.id("exampleInputPassword1"));



    public boolean validName(String Name) {

        if (Name.length() <= 50) {
            for (int i = 0; i < Name.length(); i++) {
                char ch = Name.charAt(i);
                if (Character.isLetter(ch) || ch == ' ') {
                    continue;
                }
                return false;


            }


        }
        return true;
    }
    public boolean validEmail(String Email) {
        if (Email.length() <= 255 && Email.contains("@") ) {

//   !!!!!!!
        }return true ;
    }

    public boolean validPassword (String password) {
       if (password.length() > 15 || password.length() < 8){
           System.out.println("Password must be less than 15 and more than 8 characters in length.");
           return false;
       }
String upperCaseChars = "(.*[A-Z].*)";
if (!password.matches(upperCaseChars ))
{
System.out.println("Password must have at least one uppercase character");
return false;
}
String lowerCaseChars = "(.*[a-z].*)";
if (!password.matches(lowerCaseChars ))
{
System.out.println("Password must have at least one lowercase character");
return false;
}
String numbers = "(.*[0-9].*)";
if (!password.matches(numbers ))
{
System.out.println("Password must have at least one number");
return false;
}
//String specialChars = "(.*[@,#,$,%].*$)";
//if (!password.matches(specialChars ))
//{
//System.out.println("Password must have at least one special character among @#$%");
//return false;
//}
return true;

}
}






//   private WebElement usernameField1 = Driver.getDriver().findElement(By.id("ctl00_MainContent_username"));
//
//
//
//
//
//
//    public WebElement getUsernameField() {
//        return usernameField1;
//    }
//
//    public void loginWithValidCredentials(){
//        login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
//    }
//
//    public void login(String username, String password){
//        usernameField1.sendKeys(username, Keys.TAB, password, Keys.ENTER);
//    }
//}
