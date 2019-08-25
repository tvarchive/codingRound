package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import supportlibraries.ScriptHelper;

public class FlightBookingPage extends PageBase {

	@FindBy(id = "OneWay")
	WebElement check_OneWayTrip;

	@FindBy(css = "[id='FromTag']")
	WebElement input_FromTag;

	@FindBy(id = "ToTag")
	WebElement input_ToTag;

	@FindBy(xpath = "//*[@id='ui-id-1']")
	WebElement dropDown_FromLabel;

	@FindBy(xpath = "//*[@id='ui-id-2']")
	WebElement dropDown_ToLabel;

	@FindBy(xpath = "//*[@id='ui-datepicker-div']//div/table/tbody/tr[3]/td[7]/a")
	WebElement link_PickFromDate;

	@FindBy(id = "SearchBtn")
	WebElement button_SearchFlight;

	@FindBy(className = "searchSummary")
	WebElement div_SearchSummary;

	public String fromPlace = "Bangalore";
	public String toPlace = "Delhi";

	public FlightBookingPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
		PageFactory.initElements(driver, this);
	}

	public void clickOneWayTripOption() {
		waitForElement(check_OneWayTrip, 1000);
		click(check_OneWayTrip);
	}

	public void fillFromInput() {
		click(input_FromTag);
		input_FromTag.sendKeys(fromPlace);
	}

	public void fillToInput() {
		click(input_ToTag);
		input_ToTag.sendKeys(toPlace);
	}

	public void selectFromPlaceDropDownValue() {
		//wait for the auto complete options to appear for the origin
		waitForElement(dropDown_FromLabel, 30);
		selectValueFromOptionsList(dropDown_FromLabel, fromPlace);
	}

	public void selectToPlaceDropDownValue() {
		//wait for the auto complete options to appear for the origin
		waitForElement(dropDown_ToLabel, 30);
		selectValueFromOptionsList(dropDown_ToLabel, toPlace);
	}

	public void selectDepartOnDate() {
		click(link_PickFromDate);
	}

	public void searchForOneWayFilght() {
		click(button_SearchFlight);
	}

	public boolean verifySummaryPageIsDisplayed() {
		waitForElement(div_SearchSummary, 2000);
		
		return isElementPresent(div_SearchSummary);
	}
	
}
