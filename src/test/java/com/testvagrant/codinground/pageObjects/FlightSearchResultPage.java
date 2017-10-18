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
	
	public boolean is_user_on_flight_search_result_page(){
		boolean flag1 = false;
		boolean flag2 = false;
		
		flag1 = is_displayed(label_top_search_result_heading);
		flag2 = is_displayed(section_flight_list);
		
		return flag1 && flag2;
	}
	
	
	
}
