package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelSearchPage extends BasePage{

	public HotelSearchPage(WebDriver driver) {
		super(driver);
	}
	
	    @FindBy(id = "Tags")
	    private WebElement input_hotel_city;
	    
	    @FindBy(id = "CheckInDate")
	    private WebElement input_checkin;
	    
	    @FindBy(id = "CheckOutDate")
	    private WebElement input_checkout;
	    
	 
	    @FindBy(id = "SearchHotelsButton")
	    private WebElement searchButton;

	    @FindBy(id = "travellersOnhome")
	    private WebElement select_travellers;
	    
	    
	    public HotelSearchResultsPage search_hotels(String city, Strin checkin, String checkout, String traveller){
	    	
	    }
}
