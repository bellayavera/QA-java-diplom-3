package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    private final WebDriver driver;

    public static final By exitButton = By.xpath(".//button[text()='Выход']");

    public static final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    public static final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("выход из аккаунта")
    public void exit(){
        driver.findElement(exitButton).click();
    }

    @Step("переход в конструктор по кнопке 'конструктор'")
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    @Step("переход в конструктор по кнопке логотипа")
    public void clickLogoButton(){
        driver.findElement(logoButton).click();
    }

    @Step("ожидание загрузки страницы профиля")
    public void waitLoadingProfilePage(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

    @Step("проверка, что видна кнопка 'выход' на странице профиля")
    public boolean isDisplayedExitButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        return driver.findElement(exitButton).isDisplayed();
    }
}
