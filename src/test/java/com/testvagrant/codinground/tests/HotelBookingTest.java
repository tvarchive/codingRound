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
		test.flightSearchPage.launchApplication("https://www.cleartrip.com");
	}
	
	@Test
	public void hotel_valid_search(){
		test.hotelSearchPage = test.homepage.naviagte_to_hotel_search();
		test.hotelSearchResultPage = test.hotelSearchPage.search_hotels("Jaipur","20/10/2017","21/10/2017","1 room, 2 adults");
		Assert.assertTrue(test.hotelSearchResultPage.is_user_on_hotel_search_result_page());
		Reporter.log("Hotel search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.flightSearchPage.closeBrowser();
	}
}
