import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class SignInTest {

    WebDriver driver = new ChromeDriver();
    private WebDriverWait wait;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws Exception {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        //switching from default to frame (modal_window)
        //incase we want to get back to default screen , do switch to default(switchTo().defaultContent())
        driver.switchTo().frame("modal_window");
        waitForElementToBeVisible("signInButton","id");
        driver.findElement(By.id("signInButton")).click();
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

    /**
     * this function wail wait for the locator to be visible for 40 sec max.WebDriverWait will call ExpectedCondition every 500 milliseconds until it returns successfully
     * @param element
     * @param locator
     * @return
     */

    public boolean waitForElementToBeVisible( String element, String locator)
    {
        try
        {
            wait  = new WebDriverWait(driver, 40);
            if (locator.equalsIgnoreCase("id"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(element))));
            else if(locator.equalsIgnoreCase("xpath"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(element))));
            else if(locator.equalsIgnoreCase("className"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className(element))));
            else if(locator.equalsIgnoreCase("cssSelector"))
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(element))));


        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
}
