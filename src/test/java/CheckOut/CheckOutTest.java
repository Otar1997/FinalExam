package CheckOut;

import StepObject.LogInSteps;
import StepObject.ProductAdd;
import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckOutTest extends Browser {


    @Test
    public void checkout() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        ProductAdd productAdd = new ProductAdd(driver);

        getCheckoutPage(logInSteps, productAdd);
        // fill checkout with correct info
        WebElement firstName = driver.findElement(By.id(("first-name")));
        firstName.sendKeys("Otar");

        WebElement lastName = driver.findElement(By.id(("last-name")));
        lastName.sendKeys("Kvelidze");

        WebElement postalCode = driver.findElement(By.id(("postal-code")));
        postalCode.sendKeys("0173");

        // Click continue
        WebElement buyProduct = driver.findElement(By.id("continue"));
        buyProduct.click();
        //Click Finish
        WebElement finishCheckOut = driver.findElement(By.id("finish"));
        finishCheckOut.click();
        //check if URL is changed
        Assert.assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());
    }

    @Test
    public void emptyStringCheckOut() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        ProductAdd productAdd = new ProductAdd(driver);

        getCheckoutPage(logInSteps, productAdd);
        WebElement buyProduct = driver.findElement(By.id("continue"));

        String resultText = "";

        //1) Click with empty data
        WebElement firstName = driver.findElement(By.id(("first-name")));
        firstName.sendKeys("");
        buyProduct.click();
        resultText = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        //Check if we get first name error
        Assert.assertEquals("Error: First Name is required", resultText);

        //2)Click with only name
        firstName.sendKeys("name");

        WebElement lastName = driver.findElement(By.id(("last-name")));
        lastName.sendKeys("");
        buyProduct.click();
        resultText = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        //check if we get last name error
        Assert.assertEquals("Error: Last Name is required", resultText);

        //3)click with name and lastname filled
        lastName.sendKeys("last");

        WebElement postalCode = driver.findElement(By.id(("postal-code")));
        postalCode.sendKeys("");
        buyProduct.click();
        resultText = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();

        //Check if we get postal code error
        Assert.assertEquals("Error: Postal Code is required", resultText);


    }
    @Test
    public void problemUserCheckout(){
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        ProductAdd productAdd = new ProductAdd(driver);
        //login with problem user
        logInSteps.usernameFieldAction("problem_user");
        logInSteps.passwordFieldAction("secret_sauce");
        logInSteps.logInButtonAction();

        //Add one item to cart
        productAdd.addToCart("item_4_title_link", "add-to-cart-sauce-labs-backpack");

        //Go to Cart
        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        shoppingCart.click();

        //Go to checkout and fill with correct data
        WebElement checkOutButton = driver.findElement(By.id("checkout"));
        checkOutButton.click();

        WebElement firstName = driver.findElement(By.id(("first-name")));
        firstName.sendKeys("Otar");

        WebElement lastName = driver.findElement(By.id(("last-name")));
        lastName.sendKeys("Kvelidze");

        WebElement postalCode = driver.findElement(By.id(("postal-code")));
        postalCode.sendKeys("0173");

        //Click continue
        WebElement buyProduct = driver.findElement(By.id("continue"));
        buyProduct.click();

        //Check if we get error with  correct data
        Assert.assertEquals(driver.findElement(By.cssSelector("[class='error-message-container error']")).getText(),"Error: Last Name is required");
    }
    //helper methods
    private void getCheckoutPage(LogInSteps logInSteps, ProductAdd productAdd) {
        logInSteps.usernameFieldAction("standard_user");
        logInSteps.passwordFieldAction("secret_sauce");
        logInSteps.logInButtonAction();

        productAdd.addToCart("item_4_title_link", "add-to-cart-sauce-labs-backpack");

        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        shoppingCart.click();

        WebElement checkOutButton = driver.findElement(By.id("checkout"));
        checkOutButton.click();
    }


}

