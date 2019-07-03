package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.HotelBookingPage;
import com.codinground.pages.SearchSummaryPage;
import com.codinground.reportutils.ReportListener;
import com.codinground.uicommon.UiCommonLibrary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest extends ReportListener{

	  private WebDriver driver;
      private static Date today;
      private static Calendar cal;
      private static String date;
    private static HotelBookingPage objHotelPage;
    private static DriverFactory objDriverFactory;
    private static SearchSummaryPage objSearchSummaryPage;
    private static FlightBookingPage objFlightPage;
 
    @BeforeTest
   public void before() {
 	objDriverFactory = new DriverFactory();
 	objDriverFactory.launchUrl();
 	driver = objDriverFactory.getDriver();
   }
    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {

    	objFlightPage = new FlightBookingPage(driver).get();
    	objHotelPage = objFlightPage.clickHotelLnk();
    	objHotelPage = objHotelPage.enterLocationTxt("Indiranagar, Bangalore");
    	objHotelPage.pickDate(new SimpleDateFormat("d/MMMM/yyyy").format(new Date()));
    	/*pick the next day*/
    	today = new Date();
        cal = Calendar.getInstance();
      	cal.setTime(today);
      	cal.add(Calendar.DATE, 1);
      	today = cal.getTime();
      	date = new SimpleDateFormat("d/MMMM/yyyy").format(today);
    	objHotelPage.pickDate(date);
    	objHotelPage = objHotelPage.selectTravellers("1 room, 2 adults");
    	objSearchSummaryPage = objHotelPage.clickSearchBtn();
    	Assert.assertTrue(objSearchSummaryPage.verifyIfRelevant("Bangalore"));
    
    }

   
    @AfterTest
    public void after() {
    	
    	objDriverFactory.destroyDriver();
    	
    }
	

}
