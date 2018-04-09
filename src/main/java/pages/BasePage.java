package pages;

import application.App;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage {
    private WebDriver driver;

    public BasePage(App app) {
        driver = app.getDriver();
    }

    abstract WebElement uniquePageIdentifier();

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait waitForPageDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(uniquePageIdentifier()));
        return wait;
    }

    protected WebElement getElement(By by) {
        return driver.findElement(by);
    }

    protected WebDriverWait waitForElementsVisibility(List<WebElement> element, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return wait;
    }

    protected WebDriverWait waitForElementVisibility(WebElement element, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(element));
        return wait;
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void switchToFrame(WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
        }
        catch (Exception e) {
            System.out.println("No such Frame found");
        }
    }
}