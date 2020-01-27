package tests;

import com.sun.javafx.PlatformUtil;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pagesObjects.HomePage;
import utils.PropertyUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

@DriverTest
public class DynamicTestDemo {

    @BeforeEach
    public void setUp() {
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

    @DriverTest
    public void shouldThrowAnErrorIfSignInDetailsAreMissing(WebDriver driver) {
        HomePage homePage = new HomePage(driver);
        driver.get(PropertyUtils.getProperty("website_url"));
        homePage.openUserAccountMenu();
        homePage.openSignInForm();
        homePage.clickSignIn();
        Assert.assertTrue(homePage.getSignInErrorText().contains("There were errors in your submission"));
    }

    @DriverTest
    public void testWithTwoDrivers(WebDriver driver1, WebDriver driver2) {
        driver1.get(PropertyUtils.getProperty("website_url"));
        driver2.get(PropertyUtils.getProperty("website_url"));
        Assert.fail();
    }
}
