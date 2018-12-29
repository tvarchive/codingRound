package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FlightBookingPage;
import testbase.TestBase;

public class FlightBookingTest extends TestBase {
	FlightBookingPage oFlighBooking;

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		oFlighBooking = new FlightBookingPage();

		oFlighBooking.clickOnOneWayLink();

		oFlighBooking.enterAndSelectFromLocation(oProp.getProperty("FROM_CITY"));

		oFlighBooking.enterAndSelectToLocation(oProp.getProperty("TO_CITY"));

		oFlighBooking.selectFromDate(oProp.getProperty("FROM_DATE_IN_DDMMYYYY"));

		oFlighBooking.clickOnSearchButton();

		Assert.assertEquals((oFlighBooking.checkForSearchSummary()), true);

	}

}
