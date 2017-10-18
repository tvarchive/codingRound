package com.testvagrant.codinground.initializations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.testvagrant.codinground.pageObjects.FlightSearchPage;

public class Initiator {

	private WebDriver driver;
	private FlightSearchPage flightSearchPage;

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
		flightSearchPage = new FlightSearchPage(driver);
	}
	
}
