package objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;

    public static final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    public static final By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public static final By accountButton = By.xpath(".//a[@href='/account']");

    public static final By bunSectionButton = By.xpath(".//span[text()='Булки']");

    public static final By sauceSectionButton = By.xpath(".//span[text()='Соусы']");

    public static final By fillingSectionButton = By.xpath(".//span[text()='Начинки']");

    public static final By bunSectionTitle = By.xpath(".//h2[text()='Булки']");

    public static final By sauceSectionTitle = By.xpath(".//h2[text()='Соусы']");

    public static final By fillingSectionTitle = By.xpath(".//h2[text()='Начинки']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("вход по кнопке «Войти в аккаунт» на главной")
    public void enter(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(enterAccountButton));
    }

    @Step("вход через кнопку «Личный кабинет»")
    public void account(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(accountButton));
    }

    @Step("ожидание загрузки главной страницы авторизованным пользователем")
    public void waitLoadingHomePageWhenLogin(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step("ожидание загрузки главной страницы не авторизованным пользователем")
    public void waitLoadingHomePageWhenAreNotLogin(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
    }

    @Step("проверка на видимость кнопки создания заказа авторизованным пользователем на главной странице ")
    public boolean isDisplayedCreateOrderButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("нажать на кнопку выбора секции булки, соусы или начинки")
    public void clickSectionButton(By button){
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(button));
    }

    @Step("проверка видимости раздела булки, соусы или начинки")
    public Boolean isDisplayedSectionTitle(By title){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(title));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(title));
        return driver.findElement(title).isDisplayed();
    }
}
