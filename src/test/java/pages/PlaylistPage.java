package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaylistPage extends BasePage {
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    /*@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),'\" + playlistName + \"')]")
    WebElement selectPlaylist;*/
    /*@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"%s\")]")
    WebElement selectPlaylist;*/
    @FindBy(xpath = "//button[@class='del btn-delete-playlist']")
    WebElement deletePlaylistBtn;
    @FindBy(css = "div.success.show")
    WebElement parentSuccessMsg;
    @FindBy(xpath = "//button[@class='ok']")
    WebElement submitOkBtn;
    @FindBy(xpath = "//section[@id='playlists']//a[@class='active']")
    WebElement selectedPlaylist;
    @FindBy(xpath = "//section[@id='playlists']//li[contains(text(),'Edit')]")
    WebElement editOption;
    @FindBy(xpath = "//li[@class='playlist playlist editing']//input[@type='text']")
    WebElement editPlaylistField;

    @SuppressWarnings("UnusedReturnValue")
    public PlaylistPage tapDeleteEmptyPlaylistBtn() {
        deletePlaylistBtn.click();
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public PlaylistPage tapDeletePlaylistBtn() {
        deletePlaylistBtn.click();
        submitOkBtn.click();
        return this;
    }
    public WebElement waitPlaylistDeletedMsg() {
        return parentSuccessMsg;
    }

    public String getPlaylistDeletedMsg() {
        return parentSuccessMsg.getText();
    }
    public PlaylistPage contextClickOnSelectedPlaylist() {
        actions.contextClick(selectedPlaylist).perform();
        return this;
    }
    public PlaylistPage selectEditPlaylistOption() {
        editOption.click();
        return this;
    }
    public PlaylistPage editPlaylist(String newPlaylistName) {
        editPlaylistField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        editPlaylistField.sendKeys(newPlaylistName);
        editPlaylistField.sendKeys(Keys.ENTER);
        return this;
    }
    public String getPlaylistUpdatedMsg() {
        return parentSuccessMsg.getText();
    }
}
