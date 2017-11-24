import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click(); //use By.xpath("//a[@id='userAccountLink']").click();
        driver.findElement(By.id("SignIn")).click();

        driver.findElement(By.id("signInButton")).click();//Missing Action class for popup window to give username and password

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds)  //  we can use public void waitFor(int durationInMilliSeconds)
    {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setDriverPath() //  we can use public void waitFor(int durationInMilliSeconds)
    { 
        
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "Mention path for Chrome Driver"); //Mention path for Chrome Driver
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "Mention path for Chrome Driver");//Mention path for Chrome Driver
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "Mention path for Chrome Driver");//Mention path for Chrome Driver
        }
    }


}
