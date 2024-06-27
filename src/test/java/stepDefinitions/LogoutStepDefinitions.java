package stepDefinitions;

import io.cucumber.java.en.And;
import pages.HomePage;

public class LogoutStepDefinitions {
    @And("I tap Logout button")
    public void tapLogoutButton() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.logoutBtn.click();
    }
}
