package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesObjects.HomePage;
import pagesObjects.HotelsPage;
import pagesObjects.ResultsPage;
import utils.DriverManager;
import utils.PropertyUtils;


public class HotelBookingTest extends BaseTest {

    @Test
    public void shouldBeAbleToSearchForHotels() {
        WebDriver driver = DriverManager.getDriver();
        HomePage homePage = new HomePage(driver);
        driver.get(PropertyUtils.getProperty("website_url"));

        HotelsPage hotelsPage = homePage.openHotelsSearch();
        hotelsPage.enterLocality("Indiranagar, Bangalore");
        hotelsPage.enterDateDetails();
        hotelsPage.selectTravellers("1 room, 2 adults");
        ResultsPage resultsPage = hotelsPage.searchHotels();
        boolean areResultsDisplayed = resultsPage.areHotelResultsDisplayed();
        try {
            Assert.assertEquals(areResultsDisplayed, true);
        } finally {
            DriverManager.returnDriver(driver);
        }
    }
}
