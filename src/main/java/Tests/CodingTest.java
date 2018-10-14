package Tests;
import Pages.Driver;
import Pages.FlightBookingPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class CodingTest {

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        FlightBookingPage flightBookingPage=new FlightBookingPage(Driver.getDriver());
        flightBookingPage.load_FlightBookingPage();
        flightBookingPage.click_OneWay();
        flightBookingPage.enter_source("Bangalore");
        flightBookingPage.enter_destination("Delhi");
        flightBookingPage.click_date();
        flightBookingPage.click_search();
        flightBookingPage.validate_isDisplayedSearchResults();
        flightBookingPage.closeBrowser();
    }
}
