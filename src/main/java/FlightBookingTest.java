import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import codingRound.Pages.FlightBooking_Page;
import codingRound.Utilities.Page;

public class FlightBookingTest extends Page {

    @Test
    public void testThatResultsAppearForAOneWayJourney() {        
    	
    	new Page().homePage();
    	
        new Page().waitFor(2000);
        new FlightBooking_Page().selectJourneyType();
        //all fields filled in. Now click on search
        new FlightBooking_Page().selectOrigin();
        new FlightBooking_Page().selectDestination();
        new FlightBooking_Page().searchFlight();
        new Page().waitFor(5000);
        //verify that result appears for the provided journey search
        
        new FlightBooking_Page().verifySummary();

    }
    

    @AfterClass
    public void endTest() {
    	driver.quit();
    }
    

}
