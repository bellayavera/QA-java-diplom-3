import objects.HomePage;
import org.junit.Assert;
import org.junit.Test;


public class ConstructorTest extends SeleniumConfig{

    @Test
    public void enterToBunSection(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitLoadingHomePageWhenAreNotLogin();
        objHomePage.clickSectionButton(HomePage.fillingSectionButtonInactive);
        objHomePage.clickSectionButton(HomePage.bunSectionButtonInactive);

        Assert.assertTrue(driver.findElement(HomePage.bunSectionButtonActive).isDisplayed());
        Assert.assertTrue(driver.findElement(HomePage.sauceSectionButtonInactive).isDisplayed());
        Assert.assertTrue(driver.findElement(HomePage.fillingSectionButtonInactive).isDisplayed());
    }

    @Test
    public void enterToSauceSection(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitLoadingHomePageWhenAreNotLogin();
        objHomePage.clickSectionButton(HomePage.sauceSectionButtonInactive);

        Assert.assertTrue(driver.findElement(HomePage.sauceSectionButtonActive).isDisplayed());
        Assert.assertTrue(driver.findElement(HomePage.bunSectionButtonInactive).isDisplayed());
        Assert.assertTrue(driver.findElement(HomePage.fillingSectionButtonInactive).isDisplayed());
    }

    @Test
    public void enterToFillingSection(){
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.waitLoadingHomePageWhenAreNotLogin();
        objHomePage.clickSectionButton(HomePage.sauceSectionButtonInactive);
        objHomePage.clickSectionButton(HomePage.fillingSectionButtonInactive);


        Assert.assertTrue(driver.findElement(HomePage.fillingSectionButtonActive).isDisplayed());
        Assert.assertTrue(driver.findElement(HomePage.bunSectionButtonInactive).isDisplayed());
        Assert.assertTrue(driver.findElement(HomePage.sauceSectionButtonInactive).isDisplayed());
    }
}
