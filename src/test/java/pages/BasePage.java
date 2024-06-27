package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    public WebDriverWait wait;
    protected Actions actions;

    public BasePage (WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement findElementVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public WebElement findElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void clickElementWithJavaScript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    protected Boolean waitForElementToBeVisible(WebElement element) {
        boolean isElementVisible = false;
        try {
            findElementVisibility(element);
            isElementVisible = true;
        } catch (TimeoutException e) {
            System.out.println("Web element is not found");
            e.printStackTrace();
        }
        return isElementVisible;
    }
}
