package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyUtils;
import utils.WaitUtils;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class BasePage {
    protected final WebDriver driver;
    static WaitUtils waitUtils = new WaitUtils();

    @FindBy(className = "progress")
    private WebElement progressBar;

    BasePage(WebDriver driver) {
        this.driver = driver;
        initElements();
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

    public static void selectFirstAutoCompleteOption(List<WebElement> options, WebDriver driver) {
        waitUtils.waitForElementsVisibility(options, 10, driver);
        options.get(0).click();
    }

    public WebDriverWait waitForPageDisplay() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(uniquePageIdentifier()));
        return wait;
    }

    WebDriverWait waitForInvisibilityOfProgressBar() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.invisibilityOf(progressBar));
        return wait;
    }

    public void openWebsite() {
        driver.get(PropertyUtils.getProperty("website_url"));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    static <T extends BasePage> T createPageAndWaitForDisplay(WebDriver webDriver, Class<T> pageClass) {
        try {
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            T newPage = constructor.newInstance(webDriver);
            newPage.waitForInvisibilityOfProgressBar();
            return newPage;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to initialize a page of type:" + pageClass.getName());
            return null;
        }
    }
}
