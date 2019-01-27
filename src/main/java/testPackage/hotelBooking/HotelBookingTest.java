package testPackage.hotelBooking;
import org.testng.annotations.Test;

import testUtils.TestConfig;

public class HotelBookingTest extends TestConfig {


    @Test
    public void shouldBeAbleToSearchForHotels() {

    	HotelBookingPageObjects hbo=new HotelBookingPageObjects(getdriver());
        getdriver().get("https://www.cleartrip.com/");
        
        hbo.clickHotelLink();
        hbo.serachHotelByLocality("Indiranagar, Bangalore");
        hbo.selectTraveller("1 room, 2 adults");
        hbo.clickSearchBtn();
        hbo.checkSearchSummaryAppreard();
        
    }

   

}
