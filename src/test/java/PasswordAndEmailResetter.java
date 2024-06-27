import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import stepDefinitions.BaseDefinition;
import stepDefinitions.ConfigReader;

public class PasswordAndEmailResetter {
    public static void resetPassword() {
        String email_initial_1 = ConfigReader.getProperty("email_init_1");
        String password_initial_1 = ConfigReader.getProperty("password_init_1");
        String password_updated_1 = ConfigReader.getProperty("password_updated_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());

        loginPage.login(email_initial_1, password_updated_1);
        homePage.findElementClickable(homePage.userAvatarIcon);
        homePage.userAvatarIcon.click();
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password_updated_1);
        profilePage.newPasswordField.clear();
        profilePage.newPasswordField.sendKeys(password_initial_1);
        profilePage.saveProfileBtn.click();
    }
    public static void resetEmail() {
        String email_initial_1 = ConfigReader.getProperty("email_init_1");
        String password_initial_1 = ConfigReader.getProperty("password_init_1");
        String email_updated_1 = ConfigReader.getProperty("email_updated_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());

        loginPage.login(email_updated_1, password_initial_1);
        homePage.findElementClickable(homePage.userAvatarIcon);
        homePage.userAvatarIcon.click();
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password_initial_1);
        profilePage.profileEmailAddressField.clear();
        profilePage.profileEmailAddressField.sendKeys(email_initial_1);
        profilePage.saveProfileBtn.click();
    }
    public static void resetEmailAndPassword() {
        String email_initial_1 = ConfigReader.getProperty("email_init_1");
        String password_initial_1 = ConfigReader.getProperty("password_init_1");
        String email_updated_1 = ConfigReader.getProperty("email_updated_1");
        String password_updated_1 = ConfigReader.getProperty("password_updated_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());

        loginPage.login(email_updated_1, password_updated_1);
        homePage.findElementClickable(homePage.userAvatarIcon);
        homePage.userAvatarIcon.click();
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password_updated_1);
        profilePage.profileEmailAddressField.clear();
        profilePage.profileEmailAddressField.sendKeys(email_initial_1);
        profilePage.newPasswordField.clear();
        profilePage.newPasswordField.sendKeys(password_initial_1);
        profilePage.saveProfileBtn.click();
    }
}
