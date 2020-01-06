package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;


public abstract class BasePage {
    protected final WebDriver driver;
    WaitUtils waitUtils;


    BasePage(WebDriver driver) {
        this.driver = driver;
        initElements();
        this.waitUtils = new WaitUtils();
    }

    private void initElements() {
        PageFactory.initElements(driver, this);
    }

    abstract WebElement uniquePageIdentifier();

    void switchToFrame(WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
        } catch (Exception e) {
            System.out.println("No such Frame found");
        }
    }

    boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    WebElement getElement(By by) {
        return driver.findElement(by);
    }

    public WebDriverWait waitForPageDisplay(final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(uniquePageIdentifier()));
        return wait;
    }
}
