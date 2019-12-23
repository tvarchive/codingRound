package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultsPage;

public class FlightBookingTest extends BaseTest {
    private HomePage homePage;
    private ResultsPage resultsPage;

    public FlightBookingTest() {
        homePage = new HomePage(getApp());
        resultsPage = new ResultsPage(getApp());
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        homePage.selectTripType(HomePage.TripType.ONE_WAY);
        homePage.enterOrigin("Bangalore");
        homePage.enterDestination("Delhi");
        homePage.selectRandomDepartureDate();
        homePage.searchFlights();
        resultsPage.waitForPageDisplay();
        Assert.assertTrue(resultsPage.isSearchSummaryPresent());
    }


}
