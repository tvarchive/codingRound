import org.testng.annotations.Test;

public class HotelBookingTest extends TestBase {

    @Test
    public void shouldBeAbleToSearchForHotels() {

        HotelBooking hotelBooking = new HotelBooking(driver);

        driver.get("https://www.cleartrip.com/");

        hotelBooking.click();

        hotelBooking.setLocality("Indiranagar, Bangalore");

        hotelBooking.selectCheckInAndCheckoutDate();

        hotelBooking.searchHotelclick();


    }


}
