import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    WebDriver driver;
    WebElement element = null;
    Utility oUtility;
    String BASE_URL = "";
    Properties propObj;
    String environment = "";
    ChromeOptions options = null;
    ActionSupport actions;
    @BeforeSuite
    public void setupSuiteConfig() {
        setEnvironment();
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        actions = new ActionSupport(driver);
        oUtility = new Utility(driver);
        oUtility.setDriverPath();
        propObj = oUtility.getProperty(environment);
        BASE_URL = propObj.getProperty("BASE_URL");
    }

    @BeforeTest
    public void testSetup() {


    }

    @AfterTest
    public void testTearDown() {
    //driver.quit();
    }

    @AfterSuite
    public void tearSuiteConfig() {
        driver.quit();
    }

    public void setEnvironment() {
        environment = System.getProperty("env");
        if (environment == null) {
            environment = "stage";
        }

    }
}
