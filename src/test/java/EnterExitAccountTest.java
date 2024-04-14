import io.restassured.RestAssured;
import io.restassured.response.Response;
import objects.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class EnterExitAccountTest extends SeleniumConfig {

    @Before
    public void createUser(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String json = "{\"email\": \"test-new-email@yandex.ru\", \"password\": \"1234567\", \"name\": \"Name\"}";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/auth/register");
    }

    @Test
    public void loginToAccountWhenClickEnterButtonHomePage(){
        driver.get("https://stellarburgers.nomoreparties.site");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.enter();
        objLoginPage.waitLoadingLoginPage();
        objLoginPage.login("test-new-email@yandex.ru", "1234567");

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());
    }

    @Test
    public void loginToAccountWhenClickAccountButtonHomePage(){
        driver.get("https://stellarburgers.nomoreparties.site");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);

        objHomePage.account();
        objLoginPage.waitLoadingLoginPage();
        objLoginPage.login("test-new-email@yandex.ru", "1234567");

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());

    }

    @Test
    public void loginToAccountOnForgotPasswordPage(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        ForgotPasswordPage objForgotPasswordPage = new ForgotPasswordPage(driver);

        objForgotPasswordPage.enter();
        objLoginPage.waitLoadingLoginPage();
        objLoginPage.login("test-new-email@yandex.ru", "1234567");

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());
    }

    @Test
    public void loginToAccountOnRegisterPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);

        objRegisterPage.enter();
        objLoginPage.waitLoadingLoginPage();
        objLoginPage.login("test-new-email@yandex.ru", "1234567");

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());
    }

    @Test
    public void enterToProfilePage(){
        driver.get("https://stellarburgers.nomoreparties.site/login");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        ProfilePage objProfilePage = new ProfilePage(driver);

        objLoginPage.login("test-new-email@yandex.ru", "1234567");
        objHomePage.waitLoadingHomePageWhenLogin();
        objHomePage.account();

        Assert.assertTrue(objProfilePage.isDisplayedExitButton());
    }

    @Test
    public void enterToHomePageFromProfilePageByConstructorButton(){
        driver.get("https://stellarburgers.nomoreparties.site/login");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        ProfilePage objProfilePage = new ProfilePage(driver);

        objLoginPage.login("test-new-email@yandex.ru", "1234567");
        objHomePage.waitLoadingHomePageWhenLogin();
        objHomePage.account();
        objProfilePage.waitLoadingProfilePage();
        objProfilePage.clickConstructorButton();

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());
    }

    @Test
    public void enterToHomePageFromProfilePageByLogoButton(){
        driver.get("https://stellarburgers.nomoreparties.site/login");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        ProfilePage objProfilePage = new ProfilePage(driver);

        objLoginPage.login("test-new-email@yandex.ru", "1234567");
        objHomePage.waitLoadingHomePageWhenLogin();
        objHomePage.account();
        objProfilePage.waitLoadingProfilePage();
        objProfilePage.clickLogoButton();

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());
    }

    @Test
    public void exitFromAccount(){
        driver.get("https://stellarburgers.nomoreparties.site/login");

        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);
        ProfilePage objProfilePage = new ProfilePage(driver);

        objLoginPage.login("test-new-email@yandex.ru", "1234567");
        objHomePage.waitLoadingHomePageWhenLogin();
        objHomePage.account();
        objProfilePage.waitLoadingProfilePage();
        objProfilePage.exit();

        Assert.assertTrue(objLoginPage.isDisplayedEnterOrderButton());
    }

    @After
    public void userDeletion(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String json = "{\"email\": \"test-new-email@yandex.ru\", \"password\": \"1234567\", \"name\": \"Name\"}";
        Response responseLogin = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/auth/login");
        String accessToken = responseLogin.jsonPath().getString("accessToken");
        if (accessToken != null) {
            given()
                    .auth().oauth2(accessToken.substring(7))
                    .header("Content-type", "application/json")
                    .expect().statusCode(202)
                    .when()
                    .delete("api/auth/user");
        }
    }

}
