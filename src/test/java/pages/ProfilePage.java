package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.UUID;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(css = "[name='current_password']")
    WebElement currentPasswordField;
    @FindBy(xpath = "//input[@id='inputProfileName']")
    WebElement profileNameField;
    @FindBy(xpath = "//button[@class='btn-submit']")
    WebElement saveProfileBtn;
    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public ProfilePage provideCurrentPassword(String password) {
        currentPasswordField.clear();
        currentPasswordField.sendKeys(password);
        return this;
    }
    public ProfilePage provideNewName() {
        String newName = generateRandomName();
        profileNameField.clear();
        profileNameField.sendKeys(newName);
        return this;
    }
    public String getEnteredProfileName() {
        return profileNameField.getAttribute("value");
    }
    public ProfilePage clickSave() {
        saveProfileBtn.click();
        return this;
    }
}
