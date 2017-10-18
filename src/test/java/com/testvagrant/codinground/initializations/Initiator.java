package com.testvagrant.codinground.initializations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.testvagrant.codinground.pageObjects.FlightSearchPage;
import com.testvagrant.codinground.pageObjects.FlightSearchResultPage;
import com.testvagrant.codinground.pageObjects.HomePage;
import com.testvagrant.codinground.pageObjects.HotelSearchPage;
import com.testvagrant.codinground.pageObjects.HotelSearchResultPage;
import com.testvagrant.codinground.pageObjects.SignInModal;
import com.testvagrant.codinground.utilities.ConfigFileReader;

public class Initiator {

	private WebDriver driver;
	public HomePage homepage;
	public FlightSearchPage flightSearchPage;
	public FlightSearchResultPage flighSearchResultPage;
	public HotelSearchPage hotelSearchPage;
	public HotelSearchResultPage hotelSearchResultPage;
	public SignInModal signInModal;

	private DriverCreator drFactory;
	
	
	public Initiator(String browser){
		drFactory = new DriverCreator();
		_startBrowser(browser);
		_initPageObject();
	}
	
	private void _startBrowser(String browser){
		driver = drFactory.getDriver(browser);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	private void _initPageObject(){
		homepage = new HomePage(driver);
	}
	
}
