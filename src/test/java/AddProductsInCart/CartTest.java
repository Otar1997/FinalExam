package AddProductsInCart;

import StepObject.LogInSteps;
import StepObject.ProductAdd;
import Utils.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CartTest extends Browser {


    @Test
    public void standardUserCart() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        ProductAdd productAdd = new ProductAdd(driver);
        //login with standard user
        logInSteps.usernameFieldAction("standard_user");
        logInSteps.passwordFieldAction("secret_sauce");
        logInSteps.logInButtonAction();

        //declare product ids button ids (indexes match, for example item_1_title_link match to add-to-cart-sauce-labs-bolt-t-shirt)
        String[] productIds = {"item_4_title_link", "item_0_title_link", "item_1_title_link", "item_5_title_link", "item_2_title_link", "item_3_title_link"};
        String[] buttonIds = {"add-to-cart-sauce-labs-backpack", "add-to-cart-sauce-labs-bike-light", "add-to-cart-sauce-labs-bolt-t-shirt", "add-to-cart-sauce-labs-fleece-jacket", "add-to-cart-sauce-labs-onesie", "add-to-cart-test.allthethings()-t-shirt-(red)"};
        //Array to save cart added product names
        String[] addedProductNames = new String[6];
        //add product to carts and store names in array
        for (int i = 0; i < productIds.length; i++) {
            addedProductNames[i] = productAdd.addToCart(productIds[i], buttonIds[i]);
        }

        //Go to shopping cart
        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        shoppingCart.click();
        // check one by one if added product names is in cart
        for (int i = 0; i < addedProductNames.length; i++) {
            Assert.assertEquals(addedProductNames[i], driver.findElement(By.id(productIds[i])).getText());
        }
    }


    @Test
    public void problemUserCart() {
        WebDriver driver = openBrowser();
        LogInSteps logInSteps = new LogInSteps(driver);
        ProductAdd productAdd = new ProductAdd(driver);

        //login with problem user
        logInSteps.usernameFieldAction("problem_user");
        logInSteps.passwordFieldAction("secret_sauce");
        logInSteps.logInButtonAction();

        //store products in all items list
        List<WebElement> allItems = driver.findElements(By.className("inventory_item"));

        //add all products in cart
        String[] productIds = {"item_4_title_link", "item_0_title_link", "item_1_title_link", "item_5_title_link", "item_2_title_link", "item_3_title_link"};
        String[] buttonIds = {"add-to-cart-sauce-labs-backpack", "add-to-cart-sauce-labs-bike-light", "add-to-cart-sauce-labs-bolt-t-shirt", "add-to-cart-sauce-labs-fleece-jacket", "add-to-cart-sauce-labs-onesie", "add-to-cart-test.allthethings()-t-shirt-(red)"};
        for (int i = 0; i < productIds.length; i++) {
            productAdd.addToCart(productIds[i], buttonIds[i]);

        }

        //go to shopping cart
        driver.findElement(By.id("shopping_cart_container")).click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        // check if all products are in cart (compare by quantity)
        Assert.assertNotEquals(allItems.size(), cartItems.size());

    }


}


