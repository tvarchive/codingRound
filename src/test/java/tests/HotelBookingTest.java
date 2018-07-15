package tests;

import helpers.DriverInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@Test(alwaysRun=true, testName="HotelSearch")
	public void testCaseInitialization() throws InterruptedException {
		helpers.Log.startTestCase("TC_03" + ":" + "HotelSearch");
		DriverInit.setDriver();
		DriverInit.driverInit("https://www.cleartrip.com/");
		PageFactory.initElements(DriverInit.getDriver(), this);
		hotelLink.click();
		localityTextBox.sendKeys("Indiranagar, Bangalore");
		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();
		DriverInit.driverClose();
		helpers.Log.endTestCase("FlightBookingTest");
	}
}
