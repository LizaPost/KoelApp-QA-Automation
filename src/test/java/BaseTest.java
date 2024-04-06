import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver = null;
    protected WebDriverWait wait = null;
    protected Actions actions = null;
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @DataProvider(name = "LoginPositive")
    public Object[][] dataForLoginPositive() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92"}
        };
    }
    @DataProvider(name = "LoginNegative")
    public Object[][] dataForLoginNegative() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "12345678", "https://qa.koel.app/"},
                {"nonperson@testpro.io", "YrkeNi92", "https://qa.koel.app/"},
                {"", "YrkeNi92", "https://qa.koel.app/"},
                {"yelyzaveta.postnova@testpro.io", "", "https://qa.koel.app/"},
                {"", "", "https://qa.koel.app/"}
        };
    }
    @DataProvider(name = "LogoutPositive")
    public Object[][] dataForLogoutPositive() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "https://qa.koel.app/"}
        };
    }
    @DataProvider(name = "AddSimplePlaylist")
    public Object[][] dataToAddSimplePlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "Simple Playlist"}
        };
    }
    @DataProvider(name = "DeleteEmptyPlaylist")
    public Object[][] dataToDeleteEmptyPlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "Playlist to Delete"}
        };
    }
    @DataProvider(name = "AddSongToPlaylist")
    public Object[][] dataToAddSongToPlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "Playlist to add a song", "take my hand"}
        };
    }
    @DataProvider(name = "DeletePlaylistWithSong")
    public Object[][] dataToDeletePlaylistWithSong() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "Fullfilled playlist to delete", "take my hand"}
        };
    }
    @DataProvider(name = "RenamePlaylist")
    public Object[][] dataToRenamePlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "Playlist to rename", "Renamed playlist"}
        };
    }
    @BeforeSuite
    static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        actions = new Actions(getDriver());
        getDriver().get(BaseUrl);
    }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.78:4444";
        switch(browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);
        }
    }
    public static WebDriver getDriver() {
        return threadDriver.get();
    }
    @AfterMethod
    public void tearDown() {
        threadDriver.get().close();
        threadDriver.remove();
    }
}
