package com.testvagrant.codinground.tests;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;
import com.testvagrant.codinground.utilities.ConfigFileReader;

public class FlightBookingTest {

	Initiator test;
	ConfigFileReader config;
	
	@BeforeClass
	public void startTest(){
		config = new ConfigFileReader();
		test = new Initiator(test.config.getProperty("browser"));
		test.homepage.launchApplication(test.config.getProperty("url"));
	}
	
	@Test
	public void flight_valid_search(){
		test.flightSearchPage = test.homepage.navigateToFlightSearch();
		test.flighSearchResultPage = test.flightSearchPage.searchFlights(test.config.getProperty("from_flight"),test.config.getProperty("to_flight"));
		Assert.assertTrue(test.flighSearchResultPage.isUserOnFlightSearchResultPage());
		Reporter.log("Flight search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.homepage.closeBrowser();
	}
}
