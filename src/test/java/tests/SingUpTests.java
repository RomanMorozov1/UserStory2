package tests;

import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utils.ConfigReader;
import utils.SeleniumUtils;

import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingUpTests extends TestBase {


    @BeforeTest
    public void checkForValidRegistration(){
        String validationResult = validateRegistration(ConfigReader.getProperty("firstname"), ConfigReader.getProperty("lastname"),
                ConfigReader.getProperty("email"), ConfigReader.getProperty("password2"));
        if (validationResult == null){
            System.out.println("Registration is valid!");
        }else{
            System.out.println("Invalid registration data: " + validationResult);
            return;
        }
    }

    @Test
    public void positiveLoginNonPageObjectModel() throws InterruptedException {

        new LoginPage().SignUp.click();
        Thread.sleep(5000);
        new SignUpPage().firstname.sendKeys(ConfigReader.getProperty("firstname"), Keys.TAB,ConfigReader.getProperty("lastname"), Keys.TAB,ConfigReader.getProperty("email"),
                Keys.TAB,ConfigReader.getProperty("password2"),Keys.TAB,Keys.ENTER);
        Thread.sleep(5000);

    }


    public static String validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            return "First Name is a required field.";
        } else if (!firstName.matches("^[a-zA-Z ]{1,50}$")) {
            return "First Name should only contain letters and spaces (maximum length: 50 characters).";
        }
        return null; // No error
    }

    public static String validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return "Last Name is a required field.";
        } else if (!lastName.matches("^[a-zA-Z ]{1,50}$")) {
            return "Last Name should only contain letters and spaces (maximum length: 50 characters).";
        }
        return null; // No error
    }

    public static String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Email Address is a required field.";
        } else {
            // Email format regex pattern
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                return "Invalid Email Address format.";
            } else if (email.length() > 255) {
                return "Email Address exceeds the maximum length of 255 characters.";
            } else if (!isEmailUniqueInDatabase(email)) {
                return "Email Address is already registered.";
            }
        }
        return null;
    }

    public static String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password is a required field.";
        } else if (password.length() < 8 || password.length() > 50) {
            return "Password must be between 8 and 50 characters long.";
        } else {
            // Password must contain at least 8 characters, one uppercase letter, one lowercase letter, and one number
//            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,50}$";
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,50}$";

            if (!password.matches(passwordRegex)) {
                return "Password must contain at least one uppercase letter, one lowercase letter, and one number.";
            }
        }
        return null; // No error
    }



    public static boolean isEmailUniqueInDatabase(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isUnique = true;

        try {
            // Replace these details with your actual database connection information
            String url = "jdbc:sqlite:/path/to/your/database.db";
            String username = "your_database_username";
            String password = "your_database_password";

            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                isUnique = count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isUnique;
    }


    public static String validateRegistration(String firstName, String lastName, String email, String password) {
        String error = validateFirstName(firstName);
        if (error != null) {
            return error;
        }

        error = validateLastName(lastName);
        if (error != null) {
            return error;
        }

        error = validateEmail(email);
        if (error != null) {
            return error;
        }

        error = validatePassword(password);
        return error; // May be null if there are no errors
    }


}
