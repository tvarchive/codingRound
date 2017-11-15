package com.codetest.helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.sun.javafx.PlatformUtil;

public class StartWebDriver 
{
	protected WebDriver driver = null;
	
	@BeforeMethod(alwaysRun=true)
	public void setup()
	{
		try
		{
		setDriverPath();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}
		
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		try
		{
			driver.close();
			driver.quit();
			if(driver != null)
				driver=null;
		}catch (Exception e)
		{
			e.printStackTrace();			
		}
		
	}
	
	
	
private void setDriverPath() 
	{
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
