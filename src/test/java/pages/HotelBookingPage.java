package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import supportlibraries.ScriptHelper;

public class HotelBookingPage extends PageBase {

	@FindBy(linkText = "Hotels")
	private WebElement link_HotelLink;

	@FindBy(id = "Tags")
	private WebElement input_LocalityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement button_SearchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement select_TravellerSelection;
	
	@FindBy(xpath = "//*[@id='ui-id-1']")
	WebElement dropDown_LocationSuggestion;
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']//div/table/tbody/tr[3]/td[7]/a")
	WebElement link_PickDate;
	
	@FindBy(className = "searchSummary")
	WebElement div_SearchSummary;
	
	public String locality = "Indiranagar, Bangalore";

	public HotelBookingPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver, this);
	}

	public void selectHotelLeftNav() {
		System.out.println("Click Hotel Left Nav");
		click(link_HotelLink);
	}
	
	public void searchForLocality() {
		waitForElement(input_LocalityTextBox, 2000);
		input_LocalityTextBox.clear();
		input_LocalityTextBox.sendKeys(locality);
	}
	
	public void selectTravellerSection() {
		String travellersValue = "1 room, 2 adults";
		selectByVisibleText(select_TravellerSelection, travellersValue);
		System.out.println(travellersValue+" value is selected");
	}
	
	public void selectLocationSuggestion() {
		waitForElement(dropDown_LocationSuggestion, 10);
		selectValueFromOptionsList(dropDown_LocationSuggestion, locality);
	}
	
	public void selectFromAndToDateFromPicker() {
		click(link_PickDate);
		System.out.println("Select Checkout date");
		click(link_PickDate);
	}
	
	public void clickSearchButton() {
		click(button_SearchButton);
	}
	
	public boolean verifyHotelResultsPageIsDisplayed() {
		waitForElement(div_SearchSummary, 20);
		
		if(isElementPresent(div_SearchSummary))
			return true;
		else 
		return false;
	}

}
