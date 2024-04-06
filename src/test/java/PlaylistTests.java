import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PlaylistPage;

public class PlaylistTests extends BaseTest {
    @Test()
    public void createSimplePlaylist(String email, String password, String playlistName) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        String expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";

        loginPage.login(email, password);
        homePage.clickCreatePlaylistBtn()
                .selectCreateSimplePlaylistOption()
                .enterPlaylistName(playlistName);
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.waitPlaylistCreatedMsg(), expectedPlaylistCreatedMsg));
        Assert.assertEquals(homePage.getPlaylistCreatedMsg(), expectedPlaylistCreatedMsg);
    }
    @Test(dataProvider = "DeleteEmptyPlaylist", dataProviderClass = BaseTest.class)
    public void deleteEmptyPlaylist(String email, String password, String playlistName) throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());

        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistName + ".\"";

        loginPage.login(email, password);
        homePage.clickCreatePlaylistBtn()
                .selectCreateSimplePlaylistOption()
                .enterPlaylistName(playlistName);
        playlistPage.tapDeleteEmptyPlaylistBtn();
        wait.until(ExpectedConditions.textToBePresentInElement(playlistPage.waitPlaylistDeletedMsg(), expectedPlaylistDeletedMsg));

        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
    @Test(dataProvider = "DeletePlaylistWithSong", dataProviderClass = BaseTest.class)
    public void deletePlaylistWithSong(String username, String password, String playlistName, String song) throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());

        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistName + ".\"";

        loginPage.login(username, password);
        homePage.clickCreatePlaylistBtn()
                .selectCreateSimplePlaylistOption()
                .enterPlaylistName(playlistName)
                .searchSong(song)
                .viewAll()
                .selectFirstSongOnList()
                .addSelectedSongToExistingPlaylist(playlistName);
        homePage.selectPlaylistToDelete("Playlist with song to Delete");
        playlistPage.tapDeletePlaylistBtn();
        wait.until(ExpectedConditions.textToBePresentInElement(playlistPage.waitPlaylistDeletedMsg(), expectedPlaylistDeletedMsg));

        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
    @Test(dataProvider = "RenamePlaylist", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String email, String password, String playlistName, String newPlaylistName) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());
        String expectedPlaylistUpdatedMsg = "Updated playlist \"" + newPlaylistName + ".\"";
        loginPage.login(email, password);
        homePage.clickCreatePlaylistBtn()
                .selectCreateSimplePlaylistOption()
                .enterPlaylistName(playlistName)
                .selectPlaylist(playlistName);
        playlistPage.contextClickOnSelectedPlaylist()
                .selectEditPlaylistOption()
                .editPlaylist(newPlaylistName);

        Assert.assertEquals(playlistPage.getPlaylistUpdatedMsg(), expectedPlaylistUpdatedMsg);
    }
}
