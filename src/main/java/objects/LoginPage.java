package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    public static final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");

    public static final By passField = By.xpath(".//input[@name='Пароль']");

    public static final By enterButton = By.xpath(".//button[text()='Войти']");

    public static final By errorPassMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("авторизация пользователя")
    public void login(String email, String pass){
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passField).sendKeys(pass);
        driver.findElement(enterButton).click();
    }

    @Step("ожидание загрузки страницы авторизации")
    public void waitLoadingLoginPage(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(enterButton));
    }

    @Step("проверка перехода на страницу авторизации после регистрации")
    public boolean isDisplayedEnterOrderButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        return driver.findElement(enterButton).isDisplayed();
    }

    @Step("проверка ошибки ввода неправильного пароля при регистрации")
    public boolean isDisplayedErrorPassMessage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(errorPassMessage));
        return driver.findElement(errorPassMessage).isDisplayed();
    }
}
