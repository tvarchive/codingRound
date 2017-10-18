package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelSearchResultPage extends BasePage{

	public HotelSearchResultPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(css=".searchSummary Strong")
	private WebElement label_top_search_result_heading;
	
	@FindBy(xpath= "//div[contains(@id,'ResultContainer')][last()]//nav[@class='hotelsList']")
	private WebElement hotel_list;
	
	public boolean isUserOnHotelSearchResultPage(){
		boolean flag1 = false;
		boolean flag2 = false;
		
		flag1 = isDisplayed(label_top_search_result_heading);
		flag2 = isDisplayed(hotel_list);
		
		return flag1 && flag2;
	}
	
	
	
}
