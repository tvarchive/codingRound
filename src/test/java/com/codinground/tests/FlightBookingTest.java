package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.SearchSummaryPage;
import com.codinground.uicommon.ModelTypeUi;
import com.codinground.uicommon.UiCommonLibrary;
import com.sun.javafx.PlatformUtil;

import org.apache.commons.logging.impl.Log4JLogger;
import org.junit.Before;
import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {
    
	
   private static WebDriver driver;
   private static FlightBookingPage objFlightPage;
   private static DriverFactory objDriverFactory;
   private static UiCommonLibrary objCommonLib; 
   private static SearchSummaryPage objSearchSummaryPage;
    
@BeforeTest
  public void before() {
	objDriverFactory = new DriverFactory();
	objDriverFactory.launchUrl();
	driver = objDriverFactory.getDriver();
	objCommonLib = new UiCommonLibrary(driver);
  }

    @Test
    public void testThatResultsAppearForAOneWayJourney() { 
       
    	objFlightPage = new FlightBookingPage(driver).get();
    	
        objFlightPage = objFlightPage.clickOneWay();
        Assert.assertTrue(objFlightPage.chechIfOnWayClicked(ModelTypeUi.ATTRIBUTE_CHECKED));
        objFlightPage = objFlightPage.enterDepartFrom("Bangalore");
        Assert.assertTrue(objFlightPage.checkIfFromEntered(ModelTypeUi.BANGALORE));
        objFlightPage = objFlightPage.enterToArrive("New Delhi");
        Assert.assertTrue(objFlightPage.checkIfToEntered(ModelTypeUi.NEW_DELHI));
        objFlightPage = new FlightBookingPage(driver).get();
        objFlightPage.pickFromDate("2/september/2019");
        objSearchSummaryPage = objFlightPage.clickSearchBtn();
        Assert.assertTrue(objSearchSummaryPage.verifyIfRelevant("Bangalore", "New Delhi"));
    }

@AfterTest
public void after() {
	
	driver.quit();
	
}
  
}
