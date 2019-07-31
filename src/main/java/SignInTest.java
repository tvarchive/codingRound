import com.sun.javafx.PlatformUtil;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SignInTest {

	    
	WebDriver driver;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

    	setDriverPath();        
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Your trips")).click();
        waitFor(2000);
        driver.findElement(By.id("SignIn")).click();
        waitFor(2000);
        //switching to frames        
	    driver.switchTo().frame("modal_window"); // Switching to innerframe
        waitFor(2000);
        driver.findElement(By.id("signInButton")).click();
        waitFor(2000);
        String errors1 = driver.findElement(By.id("errors1")).getText();
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
