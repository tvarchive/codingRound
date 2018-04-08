package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}