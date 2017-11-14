package com.codetest.tests;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codetest.helper.AjaxHelper;
import com.codetest.helper.LoggerHelper;
import com.codetest.helper.StartWebDriver;
import com.codetest.helper.WaitHelper;

public class FlightBookingTest extends StartWebDriver{
	
	public static  Logger logger = LoggerHelper.getLogger(FlightBookingTest.class);
	
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	
        driver.get("https://www.cleartrip.com/");
        logger.info("launched cleartrip website");
        
        WaitHelper.customewait(driver,By.id("OneWay")).click();
                
        driver.findElement(By.id("FromTag")).clear();
        logger.info("Cleared from test field");
        
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");
        logger.info("Entred bangalore in from text field");

        //wait for the auto complete options to appear for the origin

        WaitHelper.customewait(driver,By.id("ui-id-1"));
        
        //genaric method to select from dropdown
        
        AjaxHelper.select(driver,By.id("ui-id-1") , "Bangalore");
        logger.info("Select Bangalore from autocomplete list");
        
        WaitHelper.customewait(driver,By.id("ToTag")).clear();
        logger.info("Wait till destination textbox is available");
        
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");        
        logger.info("Typed Delhi");

        //wait for the auto complete options to appear for the destination

        WaitHelper.customewait(driver, By.id("ui-id-2"));
        logger.info("wait till autocomplete feild is available");
        
        AjaxHelper.select(driver, By.id("ui-id-2"), "Delhi");
        logger.info("select delhi from autocomplete dropdown");
        
        //selecting a date should be parameteraised rather than hardcoding the xpath 
        List<WebElement> monthList = driver.findElements(By.className("ui-datepicker-month"));
    	if(monthList.get(0).getText().contains("November")){
    		List<WebElement> monthTableElement = driver.findElements(By.className("calendar"));
    		List<WebElement> dateElements = monthTableElement.get(0).findElements(By.tagName("a"));
    		for(WebElement date:dateElements){
    			if(date.getText().contains("19")){
    				date.click();
    				break;
    			}
    		}
    		
    	}else{
    		List<WebElement> monthTableElement = driver.findElements(By.className("calendar"));
    		List<WebElement> dateElements = monthTableElement.get(1).findElements(By.tagName("a"));
    		for(WebElement date:dateElements){
    			if(date.getText().contains("19")){
    				date.click();
    				break;
    			}
    		}
    	}
    	logger.info("date Nov 19 is selected from the calander");

        
        driver.findElement(By.id("SearchBtn")).click();
        logger.info("Clicked on search button");

        WaitHelper.customewait(driver, By.className("searchSummary"));
        logger.info("wait till the search summery element isvisible");
        //verify that result appears for the provided journey search
        Assert.assertTrue(WaitHelper.isElementPresent(driver, By.className("searchSummary")));

        
    }


     

    
}
