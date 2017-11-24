import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class SignInTest {

   static WebDriver driver;
    String browser="Chrome";
    @BeforeTest
    public void browserInstantiation(){
        if(browser.contentEquals("Firefox)){
               driver=new FirefoxDriver(); }
        else if(browser.contentEquals("chrome")){
        ssetDriverPath();
        driver=new ChromeDriver();
        }else
         {          
             System.set.Proeprty("webdriver.ie.driver","path of ie driver") 
         }
                                 }
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws Exception { 
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        WebElement trip=driver.findElement(By.linkText("Your trips"));
        clickOnElement(trip);
        WenbElement signIn=driver.findElement(By.id("SignIn"));
        clickOnElement(signIn);

       WebElement signInButton= driver.findElement(By.id("signInButton"));
        clickOnElement(signInButton);

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.close();
    }

    public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setDriverPath() {
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
    public void clickOnElement(WebElement element){
        Actions act=new Actions(driver);
        act.click(element).build().perform();
}
