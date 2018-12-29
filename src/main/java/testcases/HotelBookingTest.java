package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HotelBookingPage;
import testbase.TestBase;

public class HotelBookingTest extends TestBase {

	HotelBookingPage oHotelPage;

	@Test
	public void shouldBeAbleToSearchForHotels() {

		oHotelPage = new HotelBookingPage();

		oHotelPage.clickonHotelLink();
		oHotelPage.enterLocality(oProp.getProperty("LOCALITY"));
		oHotelPage.optFortravelselction(oProp.getProperty("TRAVEL_SELECTION"));

		Assert.assertFalse(oHotelPage.checkForSearchBtn());
	}

}
