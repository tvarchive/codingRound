package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.sun.jna.platform.win32.Wdm.KEY_BASIC_INFORMATION;

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
	    
	    @FindBy(css="#ui-id-1 li:nth-child(2) a")
		private WebElement first_from_city_in_list;
	    
	    
	    public HotelSearchResultPage search_hotels(String city, String traveller){
	    	input_hotel_city.sendKeys(city);
	    	wait.explicitWaitForElement(first_from_city_in_list);
	    	first_from_city_in_list.click();
	    	
	    	input_checkin.click();
	    	input_checkin.sendKeys(Keys.RETURN);
	    	
	    	input_checkout.click();
	    	input_checkout.sendKeys(Keys.RETURN);
	    	
	    	select_by_text(select_travellers,traveller);
	    	
	    	searchButton.click();
	    	
	    	Reporter.log("All fields are entered for Hotel search and form submitted",true);
	    	return new HotelSearchResultPage(driver);
	    	
	    }
}
