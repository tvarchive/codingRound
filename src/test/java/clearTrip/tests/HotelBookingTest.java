package clearTrip.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import clearTrip.coreFramework.BaseTest;
import clearTrip.coreFramework.Utility;
import clearTrip.pages.HotelBookingPage;

public class HotelBookingTest extends BaseTest {
	// we will be sending the data from .json file later so collating

	private String city = "";
	private String travellers = "";

	@Test
	public void shouldBeAbleToSearchForHotels() {

		HotelBookingPage hotelBooking = new HotelBookingPage(driver);
		hotelBooking.clickHotelLink();
		hotelBooking.enterLocality(city);
		hotelBooking.selectTravellers(travellers);
		hotelBooking.clickSearch();

		// there should be a check on whether we were able to search for hotels or not
		Assert.assertTrue(hotelBooking.getNextPageTravellerRating().isDisplayed());

	}
	// unused methods removed

	//instance block to initialize the data
		{
			city = Utility.getValueFromJson("city", "HotelBookingData");
			travellers = Utility.getValueFromJson("travellers", "HotelBookingData");
		}
}