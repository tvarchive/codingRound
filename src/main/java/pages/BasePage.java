package pages;

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

    protected void clickElement(WebElement hotelLink) {
        hotelLink.click();
    }

    protected WebElement getElement(By by) {
        return driver.findElement(by);
    }

    protected WebDriverWait waitForElementsVisibility(List<WebElement> element, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
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
}