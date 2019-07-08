import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import codingRound.Pages.HotelBooking_Page;
import codingRound.Utilities.Page;

public class HotelBookingTest extends Page{

    @Test
    public void shouldBeAbleToSearchForHotels() {
    	
    	new Page().homePage();
        new HotelBooking_Page().searchHotel();
        
        }

    @AfterClass
    public void endTest() {
    	driver.quit();
    }
    

}
