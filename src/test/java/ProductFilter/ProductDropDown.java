package ProductFilter;

import DataObject.LoginData;
import StepObject.LogInSteps;
import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductDropDown extends Browser {

    @Test
    public void DropDownFilter() {
        WebDriver driver=openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        //login with standard user
        logInSteps.usernameFieldAction(LoginData.STANDARD_USER);
        logInSteps.passwordFieldAction(LoginData.CORRECT_PASSWORD);
        logInSteps.logInButtonAction();

        //click sort filter
        By productSort = By.className("product_sort_container");
        driver.findElement(productSort).click();
        //wait until filter elements is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(productSort));

        //get filter total elements
        int selectProductSize = new Select(dropDown).getOptions().size();

        //click all filter elements
        for (int i = 0; i < selectProductSize; i++) {
            WebElement currentDropDown = driver.findElement(productSort);
            currentDropDown.click();
            WebElement currentDropDownTwo = wait.until(ExpectedConditions.elementToBeClickable(productSort));

            Select currentSelectProduct = new Select(currentDropDownTwo);
            currentSelectProduct.selectByIndex(i);

        }
    }

    @Test
    public void ProblemUserDropDownFilter() {
        WebDriver driver=openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);

        //login with problem user
        logInSteps.usernameFieldAction(LoginData.PROBLEM_USER);
        logInSteps.passwordFieldAction(LoginData.CORRECT_PASSWORD);
        logInSteps.logInButtonAction();

        //click sort filter
        By productSort = By.className("product_sort_container");
        driver.findElement(productSort).click();

        //click second option
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(productSort));
        Select selectProduct = new Select(dropDown);

        selectProduct.selectByIndex(1);;


        //check if after we clicked second nothing is changed
        Assert.assertNotEquals(selectProduct.getFirstSelectedOption().getText(),"Name (Z to A)");


    }
}
