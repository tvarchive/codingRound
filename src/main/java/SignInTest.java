import com.sun.javafx.PlatformUtil;

import codingRoundBase.BaseClass;
import codingRoundUtil.TimeUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends BaseClass{

   

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        BaseClass.setDriverPath();
        
       
        driver.get("https://www.cleartrip.com/");
        TimeUtil.waitFor(2000);
        
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
       
        driver.switchTo().frame("modal_window");
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

   

   
    }



