package application;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class App {
    private final WebDriver driver;
    private static App app;

    public static App getInstance() {
        if (app == null) {
            app = new App();
        }
        return app;
    }

    private App() {
        setDriverPath();
        createLogForChromeDriver();
        driver = new ChromeDriver(getChromeOptions());
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

    private void createLogForChromeDriver() {
        String currentWorkingDirectory = new File(".").getAbsolutePath();
        System.out.println("currentWorkingDirectory : "+currentWorkingDirectory);
        final String chromeDriverLogFilePath = currentWorkingDirectory + File.separator + "chromedriver.log";
        System.out.println("Logging at:" + chromeDriverLogFilePath);
        System.setProperty("webdriver.chrome.logfile", chromeDriverLogFilePath);
        System.setProperty("webdriver.chrome.verboseLogging", "true");
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void launch() {
        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void close() {
        driver.quit();
    }
}
