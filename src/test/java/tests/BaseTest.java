package tests;

import com.sun.javafx.PlatformUtil;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.DriverManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    @BeforeSuite
    public void setUp(ITestContext context) {
        setDriverPath();
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            setDriverExecutablePath("chromedriver", "geckodriver");
        } else if (PlatformUtil.isWindows()) {
            setDriverExecutablePath("chromedriver.exe", "geckodriver.exe");
        } else if (PlatformUtil.isLinux()) {
            setDriverExecutablePath("chromedriver_linux", "geckodriver 2");
        } else {
            throw new RuntimeException("Platform not supported");
        }
    }

    private void setDriverExecutablePath(String chromedriver, String geckodriver) {
        Path chromeCommonPath = Paths.get("src","main","drivers", "chromedriver");
        Path firefoxCommonPath = Paths.get("src","main","drivers", "firefox");

        System.setProperty("webdriver.chrome.driver", chromeCommonPath.resolve(chromedriver).toString());
        System.setProperty("webdriver.gecko.driver", firefoxCommonPath.resolve(geckodriver).toString());
    }

    private void createLogForChromeDriver() {
        String currentWorkingDirectory = new File(".").getAbsolutePath();
        System.out.println("currentWorkingDirectory : " + currentWorkingDirectory);
        final String chromeDriverLogFilePath = currentWorkingDirectory + File.separator + "chromedriver.log";
        System.out.println("Logging at:" + chromeDriverLogFilePath);
        System.setProperty("webdriver.chrome.logfile", chromeDriverLogFilePath);
        System.setProperty("webdriver.chrome.verboseLogging", "true");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitAllDrivers();
    }
}
