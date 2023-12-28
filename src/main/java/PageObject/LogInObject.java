package PageObject;

import org.openqa.selenium.By;

public class LogInObject {
   protected By
    usernameField =By.id("user-name"),
    passwordField =By.id("password"),
    logInButton =By.name("login-button");
}
