
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HotelBookingPage;
import testbase.TestBase;
import utils.Constants;

public class HotelBookingTest extends TestBase {

	HotelBookingPage oHotelPage;

	@Test
	public void shouldBeAbleToSearchForHotels() {

		oHotelPage = new HotelBookingPage(driver);

		oHotelPage.clickonHotelLink();
		oHotelPage.enterLocality(Constants.LOCALITY);
		oHotelPage.optFortravelselction(Constants.TRAVEL_SELECTION);

		Assert.assertFalse(oHotelPage.checkForSearchBtn());
	}

}
