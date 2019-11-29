import javafx.application.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utility {

    private FileInputStream fileInput = null;
    Properties properties = null;
    WebDriver driver;
    File file = null;

    public Utility(WebDriver driver)
    {
        this.driver = driver;
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
            file = new File("."
                    + File.separator
                    + "main"
                    + File.separator
                    + "resources"
                    + File.separator
                    + System.getProperty("env") + "_property.properties");

            fileInput = new FileInputStream(file);
            properties.load(fileInput);
            //fileInput.close();
        }
         catch (FileNotFoundException e) {
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
}

