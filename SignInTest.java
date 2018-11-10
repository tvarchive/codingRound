package com.CodeTest1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class SignInTest
{

    WebDriver driver;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing()
    {
        
    	waitFor(5000);
    	
    	driver.findElement(By.linkText("Your trips")).click();
        
        driver.findElement(By.id("SignIn")).click();
        
        waitFor(5000);
        
        driver.switchTo().frame("modal_window"); //To Handle frame "modal_window" --- Frame name
        
        waitFor(5000);

        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText(); //To get errors from webpage
        
        System.out.println(errors1); // To print errors
        
        //Validation for error
        
        /*if(errors1.contains("There were errors in your submission")) 
        {
        	System.out.println("Error Matched");
        }else
        {
        	System.out.println("Error Not Matched");
        }*/   

        
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
        driver.quit();
    }
    
    protected void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    
	@BeforeClass
	public void setupApplication()
	{
		if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "CommonJarFiles\\chromedriver");
            
            driver = new ChromeDriver();
            
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "CommonJarFiles\\chromedriver.exe"); //Should provide path of the driver
            
            driver = new ChromeDriver();            
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "CommonJarFiles\\chromedriver_linux");
            
            driver = new ChromeDriver();
        }		
		
		driver.manage().window().maximize();
		
		driver.get("https://www.cleartrip.com/");		
	}
	
	
	@AfterClass
	public void closeApplication()
	{
		driver.quit();
		
	}

}
