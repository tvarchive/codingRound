package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesObjects.HomePage;
import pagesObjects.ResultsPage;
import utils.DriverManager;

public class FlightBookingTest extends BaseTest {

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        WebDriver driver = DriverManager.getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.openWebsite();
        homePage.selectTripType(HomePage.TripType.ONE_WAY);
        homePage.enterOrigin("Bangalore");
        homePage.enterDestination("Delhi");
        homePage.selectRandomDepartureDate();
        ResultsPage resultsPage = homePage.searchFlights();
        try {
            Assert.assertTrue(resultsPage.areFlightsResultsDisplayed());
        } finally {
            DriverManager.returnDriver(driver);
        }
    }
}
