package clearTrip.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import clearTrip.coreFramework.BaseTest;
import clearTrip.coreFramework.Utility;
import clearTrip.pages.FlightBookingPage;

public class FlightBookingTest extends BaseTest {

	// we will be sending the data from .json file later so collating
	private String flightFrom = "";
	private String flightTo = "";
	private String travelDate = "";
	
	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		// the driver config and launch has been taken care of in BaseTest class
		FlightBookingPage flightBooking = new FlightBookingPage(driver);
		flightBooking.setTripType("OneWay");
		flightBooking.setSourceAirport(flightFrom);
		flightBooking.setDestinationAirport(flightTo);
		flightBooking.setDepartureDate(travelDate);
		flightBooking.clickSubmit();
		flightBooking.waitForFlightResultsReady();

		// verify that result appears for the provided journey search
		Assert.assertTrue(((flightBooking.getTitle()).toLowerCase()).contains(flightFrom.toLowerCase()));

	}

	// unused methods removed
	
	//instance block to initialize the data
	{
		flightFrom = Utility.getValueFromJson("flightFrom", "FlightBookingData");
		flightTo = Utility.getValueFromJson("flightTo", "FlightBookingData");
		travelDate = Utility.getValueFromJson("travelDate", "FlightBookingData");
		travelDate = Utility.getTPlusNthDateInFormat(2, "dd/MM/yyyy"); // so that we don't have to change date
	}

}