package test.java.testcase;

import test.java.framework.BaseTest;
import test.java.pages.FlightBookingPage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * FlightBookingTest.java
 * 
 * Contains tests for Flight booking page 
 *
 */

@Listeners(test.java.framework.TestListener.class)

public class FlightBookingTest extends BaseTest {
	
	FlightBookingPage flightBookingPage;

/**
 * Tests for search results with
 * given random parameters 
 * 
 */
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	Reporter.log("[INFO] Starting testcase 'testThatResultsAppearForAOneWayJourney'", true);
    	
    	flightBookingPage = new FlightBookingPage(driver);
    	
    	flightBookingPage.oneWaySearch("Bangalore", "Delhi", "random");
    	
    	boolean searchSummaryPresent = flightBookingPage.checkForSearchSummary();
    	
        Assert.assertTrue(searchSummaryPresent);
        
        Reporter.log("[INFO] Starting testcase 'testThatResultsAppearForAOneWayJourney'", true);
    }
}