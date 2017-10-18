package com.testvagrant.codinground.tests;

import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;

public class FlightBookingTest {

	Initiator test;
	
	@BeforeClass
	public void startTest(){
		test = new Initiator("chrome");
		test.homepage.launchApplication("https://www.cleartrip.com");
	}
	
	@Test
	public void flight_valid_search(){
		test.flightSearchPage = test.homepage.navigateToFlightSearch();
		test.flighSearchResultPage = test.flightSearchPage.searchFlights("Jaipur","Mumbai");
		Assert.assertTrue(test.flighSearchResultPage.isUserOnFlightSearchResultPage());
		Reporter.log("Flight search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.flightSearchPage.closeBrowser();
	}
}
