package com.codetest.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codetest.helper.StartWebDriver;
import com.codetest.helper.WaitHelper;

public class FlightBookingTest extends StartWebDriver{
	
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	
        driver.get("https://www.cleartrip.com/");
        
        WaitHelper.customewait(driver,By.id("OneWay")).click();
        
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        WaitHelper.customewait(driver,By.id("ui-id-1"));
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        for(WebElement li : originOptions)
        {
        	if(li.getText().contains("Bangalore"))
        	{
        		li.click();
        		break;
        	}
        }

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        WaitHelper.customewait(driver, By.id("ui-id-2"));
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        for(WebElement li : destinationOptions)
        {
        	if(li.getText().contains("Delhi,"))
        	{
        		li.click();
        		break;
        	}
        }

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

        
        driver.findElement(By.id("SearchBtn")).click();

        WaitHelper.customewait(driver, By.className("searchSummary"));
        //verify that result appears for the provided journey search
        Assert.assertTrue(WaitHelper.isElementPresent(driver, By.className("searchSummary")));

        
    }


     

    
}
