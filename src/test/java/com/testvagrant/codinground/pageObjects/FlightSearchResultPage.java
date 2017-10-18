package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightSearchResultPage extends BasePage{

	public FlightSearchResultPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css=".searchSummary Strong")
	private WebElement label_top_search_result_heading;
	
	@FindBy(xpath= "//div[contains(@id,'ResultContainer') and contains(@style,'block')]//ul[@class='listView flights']")
	private WebElement section_flight_list;
	
	public boolean isUserOnFlightSearchResultPage(){
		boolean flag1 = false;
		boolean flag2 = false;
		
		flag1 = isDisplayed(label_top_search_result_heading);
		flag2 = isDisplayed(section_flight_list);
		
		return flag1 && flag2;
	}
	
	
	
}
