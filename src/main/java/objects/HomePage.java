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

    public static final By bunSectionButtonInactive = By.xpath(".//span[text()='Булки']/parent::div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");

    public static final By sauceSectionButtonInactive = By.xpath(".//span[text()='Соусы']/parent::div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");

    public static final By fillingSectionButtonInactive = By.xpath(".//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    public static final By bunSectionButtonActive = By.xpath(".//span[text()='Булки']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");

    public static final By sauceSectionButtonActive = By.xpath(".//span[text()='Соусы']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");

    public static final By fillingSectionButtonActive = By.xpath(".//div[contains(@class,'tab_tab_type_current__2BEPc')]/span[text()='Начинки']");

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
}

