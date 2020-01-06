package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pagesObjects.HomePage;
import pagesObjects.HotelsPage;

import java.util.Random;

public class HotelBookingTest extends BaseTest {
    private HomePage homePage;
    private HotelsPage hotelsPage;

    public HotelBookingTest() {
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

    @Test(enabled = false)
    public void testFlakyBehaviour() {
        int x = new Random().nextInt() % 2;
        Assert.assertTrue(x == 0);
    }

    @Test(enabled = false)
    public void testFlakyBehaviour2() {
        int x = new Random().nextInt() % 2;
        Assert.assertTrue(x == 0);
    }
}
