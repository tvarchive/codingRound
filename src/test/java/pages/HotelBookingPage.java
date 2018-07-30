package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.Assert;

import test.java.framework.FWUtils;

/**
 * HotelBookingPage.java
 * 
 * Contains all webelements and methods 
 * to be used to perform actions on Hotel booking page
 *
 */
public class HotelBookingPage {
	
	WebDriver driver;
	FWUtils utils = new FWUtils();
	
    @FindBy(id = "Tags")
    WebElement localityTextBox;
    
    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[contains(@class,'last')]/table/tbody/tr[2]/td/a")
    WebElement dateRandom;

    @FindBy(id = "SearchHotelsButton")
    WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    WebElement travellerSelection;
	
	@FindBy(id = "ui-id-1")
	WebElement suggestions;
	
	public HotelBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillCityLocation(String searchText, String expectedCityName) {
		localityTextBox.sendKeys(searchText);
        utils.waitFor(5000);
        
        if(expectedCityName.equalsIgnoreCase("random")) {
            suggestions.findElements(By.tagName("li")).get(1).findElement(By.tagName("a")).click();
        }
        else {
        	// search expected city name in list and then select the same
        }
  	}

	public void setDateInDatePicker(String dateAsString) {
		
		if(dateAsString.equalsIgnoreCase("random")) {
			dateRandom.click();
		}
		else {
			// select provided date in date picker
		}
	}
	
	public void selectTravellers(String travellerOption) {
		
		Select select = new Select(travellerSelection);
		
		try {
			select.selectByVisibleText(travellerOption);
		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Exception while seecting traveller options in hotel booking", true);
			Assert.fail("Could not find text " + travellerOption + " in Travellers dropdown");
		}
		
	}

	public void searchHotels(String hotelLocation, String expectedCityName, String checkIn, String checkOut, String travellers) {
		fillCityLocation(hotelLocation, expectedCityName);
		setDateInDatePicker(checkIn);
		utils.waitFor(2500);
		setDateInDatePicker(checkOut);
		selectTravellers(travellers);
	    searchButton.click();
	    utils.waitFor(3000);
	}
	
	public boolean checkForSearchSummary() {
		return utils.isElementPresent(driver, By.className("searchSummary"));
	}	
}
