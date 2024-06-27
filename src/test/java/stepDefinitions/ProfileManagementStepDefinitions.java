package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProfilePage;

public class ProfileManagementStepDefinitions {
    @And("I tap avatar icon")
    public void tapAvatarIcon() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.findElementClickable(homePage.userAvatarIcon);
        homePage.userAvatarIcon.click();
    }

    /*@And("I provide current password {string}")
    public void provideCurrentPassword(String password) {
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password);
    }*/

    /*@When("I update email address {string}")
    public void updateEmailAddress(String email) {
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.profileEmailAddressField.clear();
        profilePage.profileEmailAddressField.sendKeys(email);
    }*/

    /*@And("I provide new password {string}")
    public void provideNewPassword(String password) {
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.newPasswordField.clear();
        profilePage.newPasswordField.sendKeys(password);
    }*/

    @And("I tap Save")
    public void tapSave() {
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.saveProfileBtn.click();
    }

    @And("I provide current password")
    public void provideCurrentPassword() {
        String password = ConfigReader.getProperty("password_initial_1");
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password);
    }

    @When("I update email address")
    public void updateEmailAddress() {
        String email = ConfigReader.getProperty("email_updated_1");
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.profileEmailAddressField.clear();
        profilePage.profileEmailAddressField.sendKeys(email);
    }

    @And("I provide new password")
    public void provideNewPassword() {
        String password = ConfigReader.getProperty("password_updated_1");
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.newPasswordField.clear();
        profilePage.newPasswordField.sendKeys(password);
    }
}
