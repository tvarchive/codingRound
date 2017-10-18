package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	@FindBy(id="toTag")
	private WebElement input_to_city;
	
	@FindBy(css="#ui-id-2 li:nth-child(1) a")
	private WebElement forst_to_city_in_list;
	
	@FindBy(id="DepartDate")
	private WebElement input_depart_date;
	
	@FindBy(id="SearchBtn")
	private WebElement btn_search_flights;
	
	
	
	
	
}
