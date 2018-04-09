package tests;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.App;
import pages.HomePage;
import pages.ResultsPage;

import java.util.List;

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
