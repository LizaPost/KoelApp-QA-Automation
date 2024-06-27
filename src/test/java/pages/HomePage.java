package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage (WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(css = "img.avatar")
    public WebElement userAvatarIcon;
    @FindBy(xpath = "//i[@class='fa fa-sign-out']")
    public WebElement logoutBtn;

    public WebElement getUserAvatar() {
        return userAvatarIcon;
    }
}
