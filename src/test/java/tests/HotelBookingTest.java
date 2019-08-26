package tests;

import supportlibraries.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HotelBookingPage;

public class HotelBookingTest extends TestBase {

	@Test
	public void shouldBeAbleToSearchForHotels() {

		HotelBookingPage hotelBookingPage = new HotelBookingPage(scriptHelper);

		hotelBookingPage.selectHotelLeftNav();
		hotelBookingPage.searchForLocality();
		// Selecting value from Suggestion drop down
		hotelBookingPage.selectLocationSuggestion();
		hotelBookingPage.selectFromAndToDateFromPicker();
		hotelBookingPage.selectTravellerSection();
		// Select Search Button
		hotelBookingPage.clickSearchButton();

		Assert.assertTrue(hotelBookingPage.verifyHotelResultsPageIsDisplayed(), "Hotel Summary Section is Displayed");
	}

}
