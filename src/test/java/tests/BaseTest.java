package tests;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utils.PropertyUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.out.println("Inside BeforeSuite of BaseTest");
        setDriverPath();
    }

    @BeforeTest
    public void setUpApp() {
        System.out.println("Inside BeforeTest of BaseTest");
    }

    @BeforeClass
    public void beforeCLassOfBaseTest() {
        System.out.println("Inside BeforeClass Of BaseTest");
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(PropertyUtils.getProperty("website_url"));
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
        System.out.println("currentWorkingDirectory : " + currentWorkingDirectory);
        final String chromeDriverLogFilePath = currentWorkingDirectory + File.separator + "chromedriver.log";
        System.out.println("Logging at:" + chromeDriverLogFilePath);
        System.setProperty("webdriver.chrome.logfile", chromeDriverLogFilePath);
        System.setProperty("webdriver.chrome.verboseLogging", "true");
    }

    private Capabilities getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--start-maximized");
        return options;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        System.out.println("Inside AfterClass of BaseTest");
        driver.quit();
    }
}
