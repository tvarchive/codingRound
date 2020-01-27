package tests;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.DriverManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static tests.PlatformUtil.getOS;

public class BaseTest {

    @BeforeSuite
    public void setUp(ITestContext context) {
        setDriverPath();
    }

    private void setDriverPath() {
        if (getOS().equals("darwin")) {
            setDriverExecutablePath("chromedriver", "geckodriver");
        } else if (getOS().equals("win32")) {
            setDriverExecutablePath("chromedriver.exe", "geckodriver.exe");
        } else if (getOS().equals("linux")) {
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
