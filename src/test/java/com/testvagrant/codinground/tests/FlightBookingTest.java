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
		test.flightSearchPage = test.homepage.navigate_to_flight_search();
		test.flighSearchResultPage = test.flightSearchPage.search_flight("Jaipur","Mumbai","20/10/2017");
		Assert.assertTrue(test.flighSearchResultPage.is_user_on_flight_search_result_page());
		Reporter.log("Flight search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.flightSearchPage.closeBrowser();
	}
}
