package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    public static final By nameField = By.xpath(".//label[text()='Имя']/parent::div/input");

    public static final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");

    public static final By passField = By.xpath(".//input[@name='Пароль']");

    public static final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    public static final By enterButton = By.xpath(".//a[@href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("регистрация пользователя")
    public void register(String name, String email, String pass){
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(registerButton).click();
    }

    @Step("вход через кнопку в форме регистрации")
    public void enter(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(enterButton));
    }

}
