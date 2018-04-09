package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.HotelsPage;

public class HotelBookingTest extends BaseTest {
    private HomePage homePage;
    private HotelsPage hotelsPage;

    public HotelBookingTest() {
        this.homePage = new HomePage(getApp());
        this.hotelsPage = new HotelsPage(getApp());
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {
        homePage.openHotelsSearch();
        hotelsPage.waitForPageDisplay();
        hotelsPage.enterLocality("Indiranagar, Bangalore");
        hotelsPage.selectTravellers("1 room, 2 adults");
        hotelsPage.searchHotels();
    }
}
