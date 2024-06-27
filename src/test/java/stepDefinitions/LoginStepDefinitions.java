package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;

public class LoginStepDefinitions {
    String BaseUrl = "https://qa.koel.app/";

    @Given("I open Login page")
    public void openLoginPage() {
        BaseDefinition.getThreadLocal().get(BaseUrl);
    }

    /*@When("I enter email {string}")
    public void enterEmail(String email) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }*/
    @When("I enter email {string}")
    public void enterEmails(String emailKey) {
        String email = "";
        if (!emailKey.isEmpty()) {
            email = ConfigReader.getProperty(emailKey);
        }
        //String email = ConfigReader.getProperty(emailKey);
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    /*@And("I enter password {string}")
    public void enterPassword(String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }*/
    @And("I enter password {string}")
    public void enterPasswords(String passwordKey) {
        String password = "";
        if (!passwordKey.isEmpty()) {
            password = ConfigReader.getProperty(passwordKey);
        }
        //String password = ConfigReader.getProperty(passwordKey);
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @And("I tap Submit")
    public void tapSubmit() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.clickSubmit();
    }

    @Then("I should be logged in")
    public void userIsLoggedIn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(homePage.getUserAvatar()));
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @And("I should be redirected to Home page")
    public void userIsRedirectedToHomePage() {
        String homePageUrl = "https://qa.koel.app/#!/home";
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), homePageUrl);
    }

    @Then("I should see login error")
    public void userShouldSeeLoginError() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(loginPage.showLoginError().isDisplayed());
    }

    /*@And("I log into app with valid {string} and {string}")
    public void logIntoAppWithValidCredentials(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();
    }*/

    /*@Then("I should be able to login with updated email {string} and updated password {string}")
    public void userShouldBeAbleToLoginWithUpdatedEmailAndUpdatedPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());

        //BaseDefinition.getThreadLocal().get(BaseUrl);
        loginPage.login(email, password);

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }*/

    /*@Then("I should not be able to login with old email {string} and old password {string}")
    public void userShouldNotBeAbleToLoginWithOldEmailAndOldPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());

        loginPage.login(email, password);

        Assert.assertTrue(loginPage.showLoginError().isDisplayed());
    }*/

    /*@Then("I should be able to login with updated email {string} and old password {string}")
    public void userShouldBeAbleToLoginWithUpdatedEmailAndOldPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());

        //BaseDefinition.getThreadLocal().get(BaseUrl);
        loginPage.login(email, password);

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }*/

    /*@Then("I should be able to login with old email {string} and updated password {string}")
    public void userShouldBeAbleToLoginWithOldEmailAndUpdatedPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());

        //BaseDefinition.getThreadLocal().get(BaseUrl);
        loginPage.login(email, password);

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }*/
    /*@After("@resetPassword")
    public void resetPassword(String email, String password, String updated_password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        //BaseDefinition.getThreadLocal().get(BaseUrl);

        loginPage.login(email, updated_password);
        homePage.userAvatarIcon.click();
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(updated_password);
        profilePage.newPasswordField.clear();
        profilePage.newPasswordField.sendKeys(password);
    }*/

    @And("I log into app with valid email and password")
    public void logIntoAppWithValidEmailAndPassword() {
        String email = ConfigReader.getProperty("email_initial_1");
        String password = ConfigReader.getProperty("password_initial_1");
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();
    }

    @When("I enter email")
    public void enterEmail() {
        String email = ConfigReader.getProperty("email_initial_1");
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @And("I enter password")
    public void enterPassword() {
        String password = ConfigReader.getProperty("password_initial_1");
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @When("I enter unregistered email")
    public void enterUnregisteredEmail() {
        String email = ConfigReader.getProperty("email_unregistered");
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @And("I enter unregistered password")
    public void enterUnregisteredPassword() {
        String password = ConfigReader.getProperty("password_unregistered");
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @Then("I should be able to login with updated credentials")
    public void userShouldBeAbleToLoginWithUpdatedCredentials() {
        String email = ConfigReader.getProperty("email_updated_1");
        String password = ConfigReader.getProperty("password_updated_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());

        loginPage.login(email, password);

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Then("I should not be able to login with old credentials")
    public void userShouldNotBeAbleToLoginWithOldCredentials() {
        String email = ConfigReader.getProperty("email_initial_1");
        String password = ConfigReader.getProperty("password_initial_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());

        loginPage.login(email, password);

        Assert.assertTrue(loginPage.showLoginError().isDisplayed());
    }

    @Then("I should be able to login with updated email and old password")
    public void userShouldBeAbleToLoginWithUpdatedEmailAndOldPassword() {
        String email = ConfigReader.getProperty("email_updated_1");
        String password = ConfigReader.getProperty("password_initial_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());

        loginPage.login(email, password);

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Then("I should be able to login with old email and updated password")
    public void userShouldBeAbleToLoginWithOldEmailAndUpdatedPassword() {
        String email = ConfigReader.getProperty("email_initial_1");
        String password = ConfigReader.getProperty("password_updated_1");

        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());

        loginPage.login(email, password);

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
}
