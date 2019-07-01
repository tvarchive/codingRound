package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.uicommon.ModelTypeUi;
import com.sun.javafx.PlatformUtil;


import org.junit.Before;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {
    
	
   private static WebDriver driver;
   private static FlightBookingPage objFlightPage;
    private static DriverFactory objDriverFactory;
    
@BeforeTest
  public void before() {
	objDriverFactory = new DriverFactory();
	objDriverFactory.launchUrl();
	driver = objDriverFactory.getDriver();
	
  }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        
       /* setDriverPath();
        driver= new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();
//WebDriverWait wait = new WebDriverWait(driver,5);
//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("FromTag"))));
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       

driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[0].value='Bangalore';", elem);

        //wait for the auto complete options to appear for the origin

        waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();
        
         
        
    		   driver.findElement(By.id("ToTag")).clear();
    		   driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        //driver.findElement(By.xpath("//*[@id='ui-datepicker-div']")).click();

 
driver.findElement(By.xpath(("//*[@id='ui-datepicker-div']/child::div[1]/table/tbody/tr[5]/td[7]/a"))).click();
    
        
               
        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));//validate if Bangalore to new delhi flights are shown specifically

        //close the browser
        driver.quit();
        */
        
        objFlightPage = new FlightBookingPage(driver).get();
        
        objFlightPage = objFlightPage.clickOneWay();
        Assert.assertTrue(objFlightPage.chechIfOnWayClicked(ModelTypeUi.ATTRIBUTE_CHECKED));
        objFlightPage = objFlightPage.enterDepartFrom("Bangalore");
        Assert.assertTrue(objFlightPage.checkIfFromEntered(ModelTypeUi.BANGALORE));
        objFlightPage = objFlightPage.enterToArrive("New Delhi");
        Assert.assertTrue(objFlightPage.checkIfFromEntered(ModelTypeUi.NEW_DELHI));
        objFlightPage.pickFromDate("01/07/2019");
        objFlightPage.clickSearchBtn();    
        
      
    }

@AfterTest
public void after() {
	
	driver.quit();
	
}
    /*private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
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
    }*/
}
