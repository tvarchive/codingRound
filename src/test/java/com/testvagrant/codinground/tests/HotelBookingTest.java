package com.testvagrant.codinground.tests;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;

public class HotelBookingTest {
	
	Initiator test;

	@BeforeClass
	public void startTest(){
		test = new Initiator("chrome");
		test.homepage.launchApplication("https://www.cleartrip.com");
	}
	
	@Test
	public void hotel_valid_search(){
		test.hotelSearchPage = test.homepage.navigateToHotelSearch();
		test.hotelSearchResultPage = test.hotelSearchPage.searchHotels("Jaipur","1 room, 2 adults");
		Assert.assertTrue(test.hotelSearchResultPage.isUserOnHotelSearchResultPage());
		Reporter.log("Hotel search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.flightSearchPage.closeBrowser();
	}
}
