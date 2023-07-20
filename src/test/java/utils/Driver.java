package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static WebDriver driver;

    private Driver(){}


    public static WebDriver getDriver(){
        if(driver == null){  // if the driver object is not initialized, initialize it

            String browser = System.getProperty("browser");  // read the browser from command line and assign it to browser

            if(browser == null) {  // if nothing was passed from command line
                browser = ConfigReader.getProperty("browser").toLowerCase(); // use the browser from config.properties
            }

            switch (browser) {
                case "chrome" -> driver = new ChromeDriver();
                case "edge" -> driver = new EdgeDriver();
                case "firefox" -> driver = new FirefoxDriver();
                case "chromeheadless" -> driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
                case "edgeheadless" -> driver = new EdgeDriver(new EdgeOptions().addArguments("--headless"));
                case "firefoxheadless" -> driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless"));
                case "safari" -> driver = new SafariDriver();
                default -> throw new IllegalArgumentException(browser + " -> this browser is not supported or invalid");
            }


        }

        return driver; // otherwise return the initialized driver object
    }

    public static void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }

    }

}
