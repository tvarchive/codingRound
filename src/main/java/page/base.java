package page;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriverException;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.JavascriptExecutor;

public class base {

    public Properties data = new Properties();
    static final String propertyFilePath = "/testdata.properties";
    JavascriptExecutor javaScriptExecutor;
    public static WebDriver driver;

    public void setUp() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + propertyFilePath);
        data.load(file);

        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }

        driver = new ChromeDriver();
    }

    public void loadHomePage() {
        driver.get(data.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waitForPageLoad();
    }

    public void waitForVisible(WebElement element) {
        WebDriverWait wait =
                new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForPageLoad() {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class, WebDriverException.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                String result = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
                if (result == null)
                    return false;
                else
                    return result.equals(
                            "complete");
            }
        });
        return;
    }

    public void waitForAjaxComplete() throws InterruptedException {
        while (true) {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete)
                break;
            Thread.sleep(500);
        }
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }
}




