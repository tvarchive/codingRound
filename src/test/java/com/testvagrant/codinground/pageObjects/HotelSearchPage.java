package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

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
	    
	    
	    public HotelSearchResultPage search_hotels(String city, String checkin, String checkout, String traveller){
	    	input_hotel_city.sendKeys(city);
	    	
	    	input_checkin.sendKeys(checkin);
	    	
	    	input_checkout.sendKeys(checkout);
	    	
	    	select_by_text(select_travellers,traveller);
	    	
	    	searchButton.click();
	    	
	    	Reporter.log("All fields are entered for Hotel search and form submitted",true);
	    	return new HotelSearchResultPage(driver);
	    	
	    }
}
