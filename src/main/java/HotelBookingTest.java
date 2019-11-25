import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends TestBase {


    @Test
    public void shouldBeAbleToSearchForHotels() {
        HotelBooking hotelBooking = new HotelBooking(driver);


        driver.get("https://www.cleartrip.com/");

        hotelBooking.click();
        hotelBooking.setLocality("Indiranagar, Bangalore");

       /* localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();*/

    }


}
