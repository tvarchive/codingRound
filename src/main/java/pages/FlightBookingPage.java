package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;
import utils.CommonFunctions;
import utils.Constants;

public class FlightBookingPage extends TestBase {

	@FindBy(id = "OneWay")
	private WebElement OneWayLink;

	@FindBy(id = "FromTag")
	private WebElement FromLocation;

	@FindBy(id = "ui-id-1")
	private WebElement fromDropDown;

	@FindBy(id = "ui-id-2")
	private WebElement ToDropDown;

	@FindBy(id = "ToTag")
	private WebElement ToLocation;

	@FindBy(id = "SearchBtn")
	private WebElement SearchBtn;

	private By fromOrToDropDownValues = By.tagName("li");
	private By searchSummary = By.className("searchSummary");
	private By fromDropDownValue = By.xpath("//ul[@id='ui-id-1']/descendant::a");
	private By ToDropDownValue = By.xpath("//ul[@id='ui-id-2']/descendant::a");

	public FlightBookingPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnOneWayLink() {
		CommonFunctions.explicitWaitForElement(OneWayLink, Constants.EXPLICIT_WAIT, driver);
		CommonFunctions.clickOnElement(OneWayLink);
	}

	public void enterAndSelectFromLocation(String fromCity) {

		CommonFunctions.enterOntoElement(FromLocation, fromCity);
		CommonFunctions.fluentWaitForelementLocator(fromDropDownValue, Constants.FLUENT_WAIT_TIMEOUT,
				Constants.FLUENT_WAIT_POLLING_FQ, driver);

		List<WebElement> originOptions = fromDropDown.findElements(fromOrToDropDownValues);
		CommonFunctions.clickOnElement(originOptions.get(0));

	}

	public void enterAndSelectToLocation(String ToCity) {

		CommonFunctions.enterOntoElement(ToLocation, ToCity);
		CommonFunctions.fluentWaitForelementLocator(ToDropDownValue, Constants.FLUENT_WAIT_TIMEOUT,
				Constants.FLUENT_WAIT_POLLING_FQ, driver);

		List<WebElement> originOptions = ToDropDown.findElements(fromOrToDropDownValues);
		CommonFunctions.clickOnElement(originOptions.get(0));

	}

	public void selectFromDate(String date) {

		CommonFunctions.calendarHandling(date, driver);
	}

	public void clickOnSearchButton() {
		CommonFunctions.clickOnElement(SearchBtn);
	}

	public boolean checkForSearchSummary() {

		return CommonFunctions.isElementPresent(searchSummary, driver);
	}

}
