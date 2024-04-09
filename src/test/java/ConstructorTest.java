import objects.HomePage;
import org.junit.Assert;
import org.junit.Test;


public class ConstructorTest extends SeleniumConfig{

    @Test
    public void enterToBunSection(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitLoadingHomePageWhenAreNotLogin();
        Assert.assertTrue(objHomePage.isDisplayedSectionTitle(HomePage.bunSectionTitle));
    }

    @Test
    public void enterToSauceSection(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitLoadingHomePageWhenAreNotLogin();
        objHomePage.clickSectionButton(HomePage.sauceSectionButton);
        Assert.assertTrue(objHomePage.isDisplayedSectionTitle(HomePage.sauceSectionTitle));
    }

    @Test
    public void enterToFillingSection(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitLoadingHomePageWhenAreNotLogin();
        objHomePage.clickSectionButton(HomePage.fillingSectionButton);
        Assert.assertTrue(objHomePage.isDisplayedSectionTitle(HomePage.fillingSectionTitle));
    }
}
