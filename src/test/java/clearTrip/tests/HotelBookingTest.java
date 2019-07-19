package clearTrip.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import clearTrip.coreFramework.BaseTest;
import clearTrip.pages.HotelBookingPage;

public class HotelBookingTest extends BaseTest {
	//we will be sending the data from .json file later so collating

	private String city = "Indiranagar, Bangalore";
	private String travellers = "1 room, 1 adult";
    
    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {

    	HotelBookingPage hotelBooking = new HotelBookingPage(driver);
    	hotelBooking.clickHotelLink();
    	hotelBooking.enterLocality(city);
    	hotelBooking.selectTravellers(travellers);
    	hotelBooking.clickSearch();
        
        //there should be a check on whether we were able to search for hotels or not
        Assert.assertTrue((driver.getCurrentUrl().contains("results")));
        
    }
	//unused methods removed 

}