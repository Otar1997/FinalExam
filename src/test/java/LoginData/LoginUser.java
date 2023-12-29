package LoginData;

import DataObject.LoginData;
import StepObject.LogInSteps;
import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class LoginUser extends Browser {

    @Test()
    public void loginTest() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);

        logInSteps.usernameFieldAction(LoginData.STANDARD_USER);
        logInSteps.passwordFieldAction(LoginData.CORRECT_PASSWORD);
        logInSteps.logInButtonAction();

        //checking if we logged in website successfully
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test()
    public void invalidUserTest() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);

        //getting random username and password
        String username = LoginData.fakerName();
        String password = LoginData.fakerLastName();
        String expectedResult = "Epic sadface: Username and password do not match any user in this service";
        //getting web elements from website and fill with username and password
        logInSteps.usernameFieldAction(username);
        logInSteps.passwordFieldAction(password);
        logInSteps.logInButtonAction();
        //we wait to the error container by web driver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("error-button")));
        // after we get error message, save to resulttext
        String resultText = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        // we are checking if result is correct
        Assert.assertEquals(resultText, expectedResult);


    }

    @Test
    public void performanceGlitchUser() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        //Login with Perf Glitch user
        logInSteps.usernameFieldAction(LoginData.PERFORMANCE_GLITCH_USER);
        logInSteps.passwordFieldAction(LoginData.CORRECT_PASSWORD);

        //Record start time before login button is clicked
        long startTime = System.currentTimeMillis();
        //click login
        logInSteps.logInButtonAction();

        //wait until inventory container is created(this means that product page is loaded)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_container")));
        //get end time
        long endTime = System.currentTimeMillis();
        // calculate total login time and convert to seconds
        long totalLoginTime = endTime - startTime;
        double totalLoginTimeSeconds = totalLoginTime / 1000.0;
        //check if total login time is greater than 1
        Assert.assertTrue(totalLoginTimeSeconds > 1);
    }
}
