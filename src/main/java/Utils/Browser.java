package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class Browser {
    public static WebDriver driver;

    public static WebDriver openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        return driver;
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }
}

