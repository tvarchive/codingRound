import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");

        //waitFor(2000);
        /*.......

        Here replacing static Thread.sleep call with an explicit wait,
        which will continue the execution as soon as element is found
        and not wait for the entire duration of time given.

        ............
        */

        Helper.explicitWait(driver, 2, By.linkText("Your trips")).click();

        driver.findElement(By.id("SignIn")).click();

        /*.......

        Calling waitForFrameAndSwitch Method from Helper class.
        This method will explicitly wait for the provided number of seconds or as soon as it finds the frame
        and then will automatically switch to it.

        ............
        */

        Helper.waitForFrameAndSwitch(driver, 5, "modal_window");

        Helper.explicitWait(driver, 10, By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }


//No longer needed
//    private void waitFor(int durationInMilliSeconds) {
//        try {
//            Thread.sleep(durationInMilliSeconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }

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
