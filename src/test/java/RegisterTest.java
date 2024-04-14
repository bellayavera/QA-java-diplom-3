import io.restassured.RestAssured;
import objects.HomePage;
import objects.LoginPage;
import objects.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegisterTest extends SeleniumConfig{

    @Test
    public void successfulRegister(){
        driver.get("https://stellarburgers.nomoreparties.site/register");

        RegisterPage objRegisterPage = new RegisterPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        HomePage objHomePage = new HomePage(driver);

        objRegisterPage.register("Name","test-new-email@yandex.ru","1234567");
        objLoginPage.waitLoadingLoginPage();
        objLoginPage.login("test-new-email@yandex.ru", "1234567");

        Assert.assertTrue(objHomePage.isDisplayedCreateOrderButton());
    }

    @Test
    public void registerFailWhenIncorrectPassFail(){
        driver.get("https://stellarburgers.nomoreparties.site/register");

        RegisterPage objRegisterPage = new RegisterPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);

        objRegisterPage.register("Name","test-new-email@yandex.ru","12345");

        Assert.assertTrue(objLoginPage.isDisplayedErrorPassMessage());
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

