package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseDefinition {
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    private WebDriver driver = null;
    private final int timeSeconds = 10;

    public static WebDriver getThreadLocal() {
        return threadLocal.get();
    }
    @Before
    public void setUpBrowser() throws MalformedURLException {
        threadLocal.set(pickBrowser(System.getProperty("browser")));
        threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSeconds));
        System.out.println("Browser setup by Thread " + Thread.currentThread().getId() + " and Driver reference is: " + getThreadLocal());
    }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.78:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                //FirefoxOptions optionsFirefox = new FirefoxOptions();
                //optionsFirefox.addArguments("-private");
                //return driver = new FirefoxDriver(optionsFirefox);
                return driver = new FirefoxDriver();
            case "edge":
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
                caps.setCapability("browserName", "edge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "cloud":
                return lambdaTest();
            default:
                //System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
                optionsChrome.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
                return driver = new ChromeDriver(optionsChrome);
        }
    }
    public WebDriver lambdaTest() throws MalformedURLException {
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        DesiredCapabilities hubCapabilities = new DesiredCapabilities();

        hubCapabilities.setCapability("browserName", "chrome");
        hubCapabilities.setCapability("browserVersion", "120.0");

        HashMap<String, Object> ltOptions;
        ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "yelyzaveta.postnova");
        ltOptions.put("accessKey", "WxvpCt0HJbw9TmSoIawuvCnLePHpYVsRAt1VVLp0R2cKiusmbT");
        ltOptions.put("geoLocation", "US/ORL");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("timezone", "New_York");
        ltOptions.put("build", "my run");
        ltOptions.put("project", "koel");
        ltOptions.put("name", "login tests");
        ltOptions.put("networkThrottling", "Regular 4G");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        hubCapabilities.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), hubCapabilities);
    }
    @After
    public void tearDown() {
        threadLocal.get().close();
        threadLocal.remove();
    }
}
