package com.codinground.test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codinground.utils.SeleniumUtils;

public class FlightBookingTest extends SeleniumUtils{

	@BeforeClass
	public void init() {
		loadBrowser();
	}

    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(2000);
        //driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);
		
		  List<WebElement> originOptions =
		  driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		  originOptions.get(0).click();
		 
        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        //driver.findElement(By.id("ToTag")).sendKeys(Keys.ENTER);
		
		  //select the first item from the destination auto complete list
		  List<WebElement> destinationOptions =
		  driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		  destinationOptions.get(0).click();
		 
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

    }
    
    @AfterClass
    public void afterClass() {
    	tearDown();
    }
}
