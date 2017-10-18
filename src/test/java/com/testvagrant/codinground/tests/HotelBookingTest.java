package com.testvagrant.codinground.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;

public class HotelBookingTest {
	
	Initiator test;

	@BeforeClass
	public void startTest(){
		test = new Initiator(test.config.getProperty("browser"));
		test.homepage.launchApplication(test.config.getProperty("url"));
	}
	
	@Test
	public void hotel_valid_search(){
		test.hotelSearchPage = test.homepage.navigateToHotelSearch();
		test.hotelSearchResultPage = test.hotelSearchPage.searchHotels(test.config.getProperty("city_hotel"),test.config.getProperty("travellers"));
		Assert.assertTrue(test.hotelSearchResultPage.isUserOnHotelSearchResultPage());
		Reporter.log("Hotel search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.homepage.closeBrowser();
	}
}
