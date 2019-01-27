package testPackage.flightBooking;
import org.testng.annotations.Test;

import testUtils.TestConfig;

public class FlightBookingTest extends TestConfig {

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	
    	FlightBookingPageObjects flo=new FlightBookingPageObjects(getdriver());
    	
    	getdriver().get("https://www.cleartrip.com/");
    	flo.clickOneWay();
    	flo.setFromTag("Bangalore");
    	flo.setToTag("Delhi");
    	flo.setCurrentCalenderDay();
    	flo.clickSearchBtn();
    	flo.checkSearchSummaryAppreard();


    }


   
}
