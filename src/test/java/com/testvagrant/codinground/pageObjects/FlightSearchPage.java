package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class FlightSearchPage extends BasePage{

	public FlightSearchPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id="OneWay")
	private WebElement radio_oneWay;
	
	@FindBy(id="FromTag")
	private WebElement input_from_city;
	
	@FindBy(css="#ui-id-1 li:nth-child(1) a")
	private WebElement first_from_city_in_list;
	
	@FindBy(id="ToTag")
	private WebElement input_to_city;
	
	@FindBy(css="#ui-id-2 li:nth-child(1) a")
	private WebElement first_to_city_in_list;
	
	@FindBy(id="DepartDate")
	private WebElement input_depart_date;
	
	@FindBy(id="SearchBtn")
	private WebElement btn_search_flights;
	
	public FlightSearchResultPage search_flight(String from, String to, String date){
		
		radio_oneWay.click();
		
		input_from_city.sendKeys(from);
		wait.explicitWaitForElement(first_from_city_in_list);
		first_from_city_in_list.click();
		
		
		input_to_city.sendKeys(to);
		wait.explicitWaitForElement(first_to_city_in_list);
		first_to_city_in_list.click();
		
		input_depart_date.sendKeys(date);

		btn_search_flights.click();
		
		Reporter.log("All fields entered on flight search page and form submitted",true);
		
		
		return new FlightSearchResultPage(driver);
	}
	
	
	
}
