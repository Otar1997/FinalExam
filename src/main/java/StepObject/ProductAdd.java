package StepObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductAdd {
    WebDriver driver;
    public ProductAdd (WebDriver driver1){
        driver = driver1;
    }
    public String addToCart (String productId,String buttonId){
        WebElement product = driver.findElement(By.id(productId));
       WebElement addButton= driver.findElement(By.id(buttonId));

       addButton.click();
       return product.getText();

    }

}
