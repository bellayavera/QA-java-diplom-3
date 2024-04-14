package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;

    public static final By enterButton = By.xpath(".//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("вход через кнопку в форме восстановления пароля.")
    public void enter(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(enterButton));
    }
}
