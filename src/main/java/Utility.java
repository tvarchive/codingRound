import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utility {

    //BaseTest oBaseTest;
    private FileInputStream fileInput = null;
    Properties properties = null;
    WebDriver driver;
    File file = null;
    WebDriverWait wait;

    public Utility(WebDriver driver) {
        this.driver = driver;
        //oBaseTest = new BaseTest();
    }


    public void setDriverPath() {
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

    public Properties getProperty(String env) {
        Properties properties = new Properties();

        try {
            file = new File("src"
                    + File.separator
                    + "main"
                    + File.separator
                    + "resources"
                    + File.separator
                    + "propertyFiles"
                    + File.separator
                    + env.toLowerCase() + "_property.properties");
            fileInput = new FileInputStream(file);
            properties.load(fileInput);
            //fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElementToBeVisible(By selector) {
        wait = new WebDriverWait(driver, 45);
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(selector));
    }

    public WebElement getWebElement(By selector) {
        return driver.findElement(selector);
    }

}

