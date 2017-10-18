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
	
	@FindBy(linkText="Your trips")
	private WebElement dropDown_yourTrips;
	
	@FindBy(id="SignIn")
	private WebElement btn_signIn;
	
	
	public FlightSearchPage navigateToFlightSearch(){
		link_flights.click();
		return new FlightSearchPage(driver);
	}
	
	public HotelSearchPage navigateToHotelSearch(){
		link_hotels.click();
		return new HotelSearchPage(driver);
	}
	
	public SignInModal openSignInModal(){
		dropDown_yourTrips.click();
		wait.explicitWaitForElement(dropDown_yourTrips);
		dropDown_yourTrips.click();
		return new SignInModal(driver);
	}
	
	
	
}
