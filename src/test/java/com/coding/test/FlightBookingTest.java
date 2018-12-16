package com.coding.test;

import com.coding.pages.FlightBookingPage;

import org.testng.annotations.Test;

public class FlightBookingTest extends FlightBookingPage {

	@Test(description = "Fill Details and Search Flights")
	public void testThatResultsAppearForAOneWayJourney() throws Exception {
		try {
			fillDetailsSearchFlight();
		} catch (Exception e) {
			e.printStackTrace();
		}
		switchToHomePage();
	}
}
