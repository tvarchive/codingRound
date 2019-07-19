package clearTrip.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import clearTrip.coreFramework.Utility;
import clearTrip.coreFramework.BaseTest;
import clearTrip.pages.FlightBookingPage;

public class FlightBookingTest extends BaseTest {
	
	//we will be sending the data from .json file later so collating
	String flightFrom = "Bangalore";
	String flightTo = "Delhi";
	String travelDate = "";

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		//the driver config and launch has been taken care of in BaseTest class 

		FlightBookingPage flightBooking = new FlightBookingPage(driver);
		flightBooking.setTripType("OneWay");
		flightBooking.setSourceAirport(flightFrom);
		flightBooking.setDestinationAirport(flightTo);
		travelDate = Utility.getTPlusNthDateInFormat(2, "dd/MM/yyyy");
		flightBooking.setDepartureDate(travelDate);
		flightBooking.clickSubmit();
		flightBooking.waitForFlightResultsReady();

		// verify that result appears for the provided journey search
		Assert.assertTrue(((flightBooking.getTitle()).toLowerCase()).contains(flightFrom.toLowerCase()));

	}

	//unused methods removed 
}