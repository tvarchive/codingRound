package test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.FlightBookingPage;
import page.base;
import java.io.IOException;

public class FlightBookingTest extends base {

    FlightBookingPage flightBookingPage;

    @BeforeTest
    public void setUpDriver() throws IOException {
        setUp();
        loadHomePage();
        flightBookingPage = new FlightBookingPage(driver);
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        flightBookingPage.clickOneWayOption();
        flightBookingPage.enterFromText(data.getProperty("fromText"));

        flightBookingPage.enterToText(data.getProperty("toText"));
        flightBookingPage.selectDate();
        flightBookingPage.clickSearchButton();
        Assert.assertTrue(flightBookingPage.isSearchSummaryPresent());
    }
}
