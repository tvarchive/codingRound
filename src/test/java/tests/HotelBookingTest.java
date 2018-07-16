package tests;

import helpers.DriverInit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
		localityTextBox.sendKeys("Indiranagar, Bangalore, Karnataka, India");
		Thread.sleep(2000);
		localityTextBox.sendKeys(Keys.ENTER);
//		Thread.sleep(2000);
		localityTextBox.click();
//		Thread.sleep(2000);
		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();
		String hotelsUrl = DriverInit.getDriver().getCurrentUrl();
		String expectedURL = "results?city=Bangalore&state=Karnataka&country=IN&area=Indiranagar";
		Assert.assertTrue(hotelsUrl.contains(expectedURL));		
		DriverInit.driverClose();
		helpers.Log.endTestCase("HotelBookingTest");
	}
}