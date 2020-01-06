package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagesObjects.HomePage;
import pagesObjects.HotelsPage;


public class HotelBookingTest extends BaseTest {
    private HomePage homePage;
    private HotelsPage hotelsPage;

    @BeforeClass
    public void setupPages() {
        System.out.println("Inside BeforeClass of HotelBookingTest");
        this.homePage = new HomePage(driver);
        this.hotelsPage = new HotelsPage(driver);
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {
        homePage.openHotelsSearch();
        homePage.waitForPageDisplay(driver);
        hotelsPage.enterLocality("Indiranagar, Bangalore");
        hotelsPage.selectTravellers("1 room, 2 adults");
        hotelsPage.searchHotels();
    }
}
