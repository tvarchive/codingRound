package com.testvagrant.codinground.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;
import com.testvagrant.codinground.utilities.ConfigFileReader;

public class HotelBookingTest {
	
	Initiator test;
	ConfigFileReader config;

	@BeforeClass
	public void startTest(){
		config = new ConfigFileReader();
		test = new Initiator(config.getProperty("browser"));
		test.homepage.launchApplication(config.getProperty("url"));
	}
	
	@Test
	public void hotel_valid_search(){
		test.hotelSearchPage = test.homepage.navigateToHotelSearch();
		test.hotelSearchResultPage = test.hotelSearchPage.searchHotels(config.getProperty("city_hotel"),config.getProperty("travellers"));
		Assert.assertTrue(test.hotelSearchResultPage.isUserOnHotelSearchResultPage());
		Reporter.log("Hotel search passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.homepage.closeBrowser();
	}
}
