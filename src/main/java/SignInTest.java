import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class SignInTest {

    WebDriver driver ;
    SignInTestElements sine;
    
    @BeforeTest
    public void beforeTest() {
    	setDriverPath();
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--disable-notifications");
    	driver = new ChromeDriver(options);
    	sine = new SignInTestElements(driver);
    }
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get("https://www.cleartrip.com/");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
//        waitFor(2000);
        
        //driver.findElement(By.linkText("Your trips")).click();
        sine.yourTripsElement().click();
        //driver.findElement(By.id("SignIn")).click();
        sine.signInElement().click();

        //driver.findElement(By.id("signInButton")).click();
        driver.switchTo().frame(sine.iFrameElement());
        sine.signInButtonElement().click();
        
        String errors1 = sine.errorMsgElement().getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
        driver.switchTo().parentFrame();
    }
    
    @AfterTest
    public void afterTest()
    {
    	driver.close();
    	driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @SuppressWarnings("restriction")
	private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
