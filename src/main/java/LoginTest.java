import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver driver;
    LoginPage objLogin;


    @BeforeTest
    public void setup(){
        setDriverPath();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        waitFor(2000);

    }


    @Test(priority=0)
    public void shouldThrowAnErrorIfSignInDetailsAreMissing(){

        //Create Login Page object
        objLogin = PageFactory.initElements(driver,LoginPage.class );

        //Verify login page title
        String loginPageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("booking flights"));

        //login to application
        objLogin.clickLogin();

        String errors1 = objLogin.fetchErrors();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();

    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
    }
}
