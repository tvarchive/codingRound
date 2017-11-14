package com.codetest.tests;

import com.codetest.helper.StartWebDriver;
import com.codetest.helper.WaitHelper;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SignInTest extends StartWebDriver {

  

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	
    	//capturing logs from reporter class of testNg
    	
        driver.get("https://www.cleartrip.com/");
        Reporter.log("launching website",true);
        WaitHelper.customewait(driver, By.linkText("Your trips"));
        driver.findElement(By.linkText("Your trips")).click();
        Reporter.log("clicked on your Trips link",true);
        driver.findElement(By.id("SignIn")).click();
        Reporter.log("clicked on SignIn",true);
        
        //frameId is used to switch the frame
        driver.switchTo().frame(driver.findElement(By.id("modal_window")));
        Reporter.log("Switched frame",true);
        driver.findElement(By.id("signInButton")).click();
        Reporter.log("Clicked on signIn button",true);
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Reporter.log("Got the error message",true);
        Assert.assertTrue(errors1.contains("There were errors in your submission"));        
    }

    
}
