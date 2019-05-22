import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    public static String baseUrl = "https://www.cleartrip.com/"; //All the data should be maintained in a separate text file for better reusability
    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get(baseUrl);
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        
        driver.click("SignIn");
        driver.click("signInButton");

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertFalse(errors1.contains("There were errors in your submission"), "Throwing Error!! Sign-in details are missing");
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

    public static void click(WebDriver driver,String element_id)	
	{
		getWebElement(driver, element_id).click();
	}
    
    public static WebElement getWebElement(WebDriver driver , String element_id)
	{
		return driver.findElement(By.id(element_id));
	}

}
