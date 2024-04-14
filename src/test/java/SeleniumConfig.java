import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class SeleniumConfig {
    protected WebDriver driver;

    @Step("создание драйвера браузера")
    public WebDriver getWebDriver(String browserName){
        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(options);
            case "firefox":
                return new FirefoxDriver();
        }
        return null;
    }

    @Before
    public void setup() {
        driver = getWebDriver("chrome");
    }

    @Step("закрытие браузера")
    @After
    public void tearDown() {
        driver.quit();
    }
}
