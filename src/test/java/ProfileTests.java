import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTests extends BaseTest {
    @Test(dataProvider = "LoginPositive", dataProviderClass = BaseTest.class)
    public void changeProfileName(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        ProfilePage profilePage = new ProfilePage(getDriver());

        loginPage.login(email, password);
        wait.until(ExpectedConditions.elementToBeClickable(homePage.userAvatarIcon));
        homePage.clickOnAvatar();
        profilePage.provideCurrentPassword(password)
                .provideNewName()
                .clickSave();
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.actualProfileName, profilePage.getEnteredProfileName()));

        Assert.assertEquals(homePage.actualProfileName.getText(), profilePage.getEnteredProfileName());
    }
}
