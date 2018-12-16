package com.coding.test;

import com.coding.pages.HotelBookingPage;
import org.testng.annotations.Test;

public class HotelBookingTest extends HotelBookingPage {

	@Test(description = "Fill Details and Search Hotels")
	public void testThatResultsAppearForAOneWayJourney() throws Exception {
		try {
			fillDetailsHotelBooking();
		} catch (Exception e) {
			e.printStackTrace();
		}
		switchToHomePage();
	}
}