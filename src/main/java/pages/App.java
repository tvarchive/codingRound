package pages;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class App {
    private WebDriver driver;

    public App() {
        setDriverPath();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver_linux");
        }
    }

    public void launch() {
        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void close() {
        driver.quit();
    }
}
