import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test(dataProvider = "LoginPositive", dataProviderClass = BaseTest.class)
    public void loginWithCorrectCredentials(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
    @Test(dataProvider = "LoginNegative", dataProviderClass = BaseTest.class)
    public void loginWithIncorrectCredentials(String email, String password, String BaseUrl) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();
        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
    @Test(dataProvider = "LogoutPositive", dataProviderClass = BaseTest.class)
    public void logout(String email, String password, String BaseUrl) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.login(email, password);
        homePage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
}
