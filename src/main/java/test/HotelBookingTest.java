package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.HotelBookingPage;
import page.base;

import java.io.IOException;

public class HotelBookingTest extends base {

    HotelBookingPage hotelBookingPage;

    @BeforeTest
    public void setUpDriver() throws IOException {
        setUp();
        loadHomePage();
        hotelBookingPage = new HotelBookingPage(driver);
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {
        hotelBookingPage.clickHotelLink();
        hotelBookingPage.enterLocalitySearchText(data.getProperty("localitySearchText"));
        hotelBookingPage.clickCheckInDate();
        hotelBookingPage.clickCheckOutDate();
        hotelBookingPage.selectTravellers(data.getProperty("travellers"));
    }
}
