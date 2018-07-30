package test.java.testcase;

import test.java.framework.BaseTest;
import test.java.pages.HomePage;
import test.java.pages.HotelBookingPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * HotelBookingTest.java
 * 
 * Contains tests for Hotel booking page 
 *
 */
@Listeners(test.java.framework.TestListener.class)

public class HotelBookingTest extends BaseTest {
	
	HotelBookingPage hotelBookingPage;
	HomePage homePage;

	/**
	 * Tests for search results with
	 * given random parameters 
	 * 
	 */
    @Test
    public void shouldBeAbleToSearchForHotels() {
    	Reporter.log("[INFO] Starting testcase 'shouldBeAbleToSearchForHotels'", true);
    	homePage = new HomePage(driver);
    	homePage.goToHotelsBooking();
    	
    	hotelBookingPage = new HotelBookingPage(driver);
    	hotelBookingPage.searchHotels("Indiranagar, Bangalore", "random", "random", "random", "1 room, 2 adults");
    	
    	boolean searchSummaryPresent = hotelBookingPage.checkForSearchSummary();    	
        Assert.assertTrue(searchSummaryPresent);
        Reporter.log("[INFO] Finished testcase 'shouldBeAbleToSearchForHotels'", true);
    }
}