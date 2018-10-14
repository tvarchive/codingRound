package Pages;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver driver;
    public static WebDriver getDriver()
    {
        setDriverPath();
        driver = new ChromeDriver(setChromeProperties());
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        return driver;
    }
    public static ChromeOptions setChromeProperties() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT);
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.geolocation", 2);
        ChromeOptions cOptions = new ChromeOptions();
        cOptions.addArguments("disable-infobars");
        cOptions.addArguments("--disable-notifications");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        cOptions.setExperimentalOption("prefs", prefs);
        return cOptions;
    }

    private static void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_2.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
}
