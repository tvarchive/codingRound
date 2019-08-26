package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.FlightBookingPage;
import supportlibraries.TestBase;

public class FlightBookingTest extends TestBase {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		System.out.println("Started to Validate Filght one way trip form");
		FlightBookingPage flightBookPage = new FlightBookingPage(scriptHelper);

		flightBookPage.clickOneWayTripOption();
		flightBookPage.fillFromInput();
		flightBookPage.selectFromPlaceDropDownValue();
		flightBookPage.fillToInput();
		flightBookPage.selectToPlaceDropDownValue();
		flightBookPage.selectDepartOnDate();
		flightBookPage.searchForOneWayFilght();
		// Wait for loading the Summary Page
		waitForPageLoad(1000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(flightBookPage.verifySummaryPageIsDisplayed(), "Filght Results Summary Section is displayed");

	}
}
