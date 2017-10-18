package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightSearchResultPage extends BasePage{

	public FlightSearchResultPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css=".searchSummary")
	private WebElement label_top_search_result_heading;
	
	@FindBy(xpath= "//div[@id='ResultContainer_1_2' and contains(@style,'block')]//ul[@class='listView flights']")
	private WebElement section_flight_list;
	
	
	
}
