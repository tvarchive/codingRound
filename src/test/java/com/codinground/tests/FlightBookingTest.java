package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.SearchSummaryPage;
import com.codinground.reportutils.ReportListener;
import com.codinground.uicommon.UiConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class FlightBookingTest extends ReportListener {
    
	
   

   private WebDriver driver;
   private static FlightBookingPage objFlightPage;
   private static DriverFactory objDriverFactory; 
   private static SearchSummaryPage objSearchSummaryPage;
   private static ReportListener objReportListener;
    
  @BeforeTest
  public void before() {
	objDriverFactory = new DriverFactory();
	objDriverFactory.launchUrl();
	driver = objDriverFactory.getDriver();
	driver.manage().window().maximize();
	
  }

    @Test
    public void testThatResultsAppearForAOneWayJourney() { 
       
    	objFlightPage = new FlightBookingPage(driver).get();
    	
        objFlightPage = objFlightPage.clickOneWay();
        Assert.assertTrue(objFlightPage.chechIfOnWayClicked(UiConstants.ATTRIBUTE_CHECKED));
        objFlightPage = objFlightPage.enterDepartFrom("Bangalore");
        Assert.assertTrue(objFlightPage.checkIfFromEntered(UiConstants.BANGALORE));
        objFlightPage = objFlightPage.enterToArrive("New Delhi");
        Assert.assertTrue(objFlightPage.checkIfToEntered(UiConstants.NEW_DELHI));
        objFlightPage = new FlightBookingPage(driver).get();
        objFlightPage.pickFromDate(new SimpleDateFormat("d/MMMM/yyyy").format(new Date()));
        objSearchSummaryPage = objFlightPage.clickSearchBtn();
        Assert.assertTrue(objSearchSummaryPage.verifyIfRelevant("Bangalore", "New Delhi"));
    }

 @AfterTest
 public void after() {
	
	 objDriverFactory.destroyDriver();
	 
 }
  
}
