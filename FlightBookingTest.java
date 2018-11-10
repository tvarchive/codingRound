package com.CodeTest1;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

@SuppressWarnings({ "restriction", "unused" })
public class FlightBookingTest
{

    WebDriver driver;


    @Test
    public void testThatResultsAppearForAOneWayJourney()
    {
    	       
        waitFor(2000);
        
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        
        waitFor(5000);
        
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(5000);
        
        
        List<WebElement> originOptions = driver.findElements(By.id("ui-id-1"));

        // this are all the links you like to visit
        for (WebElement webElement : originOptions)
        {
            webElement.click();
        }
        

        driver.findElement(By.id("ToTag")).clear();
        
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(5000);
        
        //select the first item from the destination auto complete list
        
        List<WebElement> destinationOptions = driver.findElements(By.id("ui-id-2"));
        
        waitFor(5000);

        // this are all the links you like to visit
        for (WebElement webElement : destinationOptions)
        {
            webElement.click();
        }

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(10000);
        
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        driver.quit();

    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
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
