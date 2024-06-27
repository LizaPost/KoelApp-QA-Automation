package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    public ProfilePage (WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(css = "[name='current_password']")
    public WebElement currentPasswordField;
    @FindBy(css = "#inputProfileEmail")
    public WebElement profileEmailAddressField;
    @FindBy(css = "#inputProfileNewPassword")
    public WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='inputProfileName']")
    public WebElement profileNameField;
    @FindBy(xpath = "//button[@class='btn-submit']")
    public WebElement saveProfileBtn;
}
