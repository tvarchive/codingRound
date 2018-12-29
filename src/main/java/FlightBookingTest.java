
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FlightBookingPage;
import testbase.TestBase;

import utils.Constants;

public class FlightBookingTest extends TestBase {
	FlightBookingPage oFlighBooking;

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		oFlighBooking = new FlightBookingPage();

		oFlighBooking.clickOnOneWayLink();

		oFlighBooking.enterAndSelectFromLocation(Constants.FROM_CITY);

		oFlighBooking.enterAndSelectToLocation(Constants.TO_CITY);

		oFlighBooking.selectFromDate(Constants.FROM_DATE_IN_DDMMYYYY);

		oFlighBooking.clickOnSearchButton();

		Assert.assertEquals((oFlighBooking.checkForSearchSummary()), true);

	}

}
