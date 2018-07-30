package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import test.java.framework.FWUtils;

/**
 * FlightBookingPage.java
 * 
 * Contains all webelements and methods 
 * to be used to perform actions on Flight booking page
 *
 */
public class FlightBookingPage {
	
	WebDriver driver;
	FWUtils utils = new FWUtils();
	
	@FindBy(id = "OneWay")
	WebElement oneWay;
	
	@FindBy(id = "FromTag")
	WebElement fromCity;
	
	@FindBy(id = "ui-id-1")
	WebElement fromCitySuggestionList;
	
	@FindBy(id = "ui-id-2")
	WebElement toCitySuggestionList;
	
	@FindBy(id = "ToTag")
	WebElement toCity;
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div[contains(@class,'last')]/table/tbody/tr[2]/td/a")
	WebElement departDateRandom;
	
	@FindBy(xpath = "//input[@title='Depart date']")
	WebElement departDatePicker;
	
	@FindBy(id = "SearchBtn")
	WebElement searchFlightBtn;
	
	public FlightBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectTripType(String tripType) {
		if(tripType.equalsIgnoreCase("One Way")) {
			oneWay.click();
		}
		else if(tripType.equalsIgnoreCase("Round Trip")) {
			// roundTrip.click();
		}
		else if(tripType.equalsIgnoreCase("Multi-city")) {
			// multicity.click();
		}
		else {
			Reporter.log("invalid trip type option", true);
			Assert.fail("invalid trip type option");
		}
	}

	public void fillFromToCity(String fromCityName, String toCityName) {
		
		fromCity.clear();
		fromCity.sendKeys(fromCityName);
		
		utils.waitFor(2000);
		fromCitySuggestionList.findElements(By.tagName("li")).get(0).click();
		
		toCity.clear();
		toCity.sendKeys(toCityName);

		utils.waitFor(2000);
		toCitySuggestionList.findElements(By.tagName("li")).get(0).click();
		
	}

	public void setTravelDate(String departDate) {
		
		departDatePicker.click();
		if(departDate.equalsIgnoreCase("random")) {
			departDateRandom.click();
		}
		else {
			// select provided date in date picker
		}
	}

	public void oneWaySearch(String fromCityName, String toCityName, String departDate) {
		oneWay.click();
		fillFromToCity(fromCityName, toCityName);
		setTravelDate(departDate);
		searchFlightBtn.click();	
		utils.waitFor(10000);
	}

	public boolean checkForSearchSummary() {
		return utils.isElementPresent(driver, By.className("searchSummary"));
	}
	
}
