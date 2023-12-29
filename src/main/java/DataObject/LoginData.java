package DataObject;

import com.github.javafaker.Faker;

public abstract class LoginData {
    public static final String STANDARD_USER = "standard_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    public static final String CORRECT_PASSWORD = "secret_sauce";
    public static final Faker FAKER = new Faker();
    public static String fakerName(){
        return FAKER.name().firstName();
    }
    public static String fakerLastName(){
        return FAKER.name().lastName();
    }



}
