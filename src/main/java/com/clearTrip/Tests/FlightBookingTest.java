package com.clearTrip.Tests;
import com.clearTrip.pages.FlightBookingPage;
import com.clearTrip.utils.DriverFactoy;
import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends FlightBookingPage {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		String toCity = "Bangalore";
		String fromCity = "Delhi";

		FlightBookingPage.clickoneWay();
		FlightBookingPage.fillFromField(toCity);
		FlightBookingPage.fillToField(fromCity);
		FlightBookingPage.pickDate();

		// all fields filled in. Now click on search
		FlightBookingPage.clickOnSearch();

		waitFor(FlightBookingPage.searchSummary());
		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

	}

}
