import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class BaseTest {

    WebDriver driver;
    Utility oUtility;
    String BASE_URL = "";
    Properties propObj;

    @BeforeSuite
    public void setupSuiteConfig() {
        System.out.println("Platform name is --" + System.getProperty("os.name"));
        driver = new ChromeDriver();
        oUtility = new Utility(driver);
        oUtility.setDriverPath();
        propObj = oUtility.getProperty(System.getProperty("env"));
        BASE_URL = propObj.getProperty("BASE_URL");
    }


    @AfterSuite
    public void tearSuiteConfig() {

    }

    public void propertyInit() {

    }
}
