package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(linkText="Flights")
	private WebElement link_flights;
	
	@FindBy(linkText="Hotels")
	private WebElement link_hotels;
	
	
	public FlightSearchPage navigate_to_flight_search(){
		link_flights.click();
		return new FlightSearchPage(driver);
	}
	
	public HotelSearchPage naviagte_to_hotel_search(){
		link_hotels.click();
		return new HotelSearchPage(driver);
	}
	
	
	
}
