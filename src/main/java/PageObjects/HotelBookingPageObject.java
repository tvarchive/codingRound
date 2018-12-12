package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

import Utils.DriverFactoy;

public class HotelBookingPageObject extends DriverFactoy {

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@BeforeTest
	public void launchApplication() {
		driver.get("https://www.cleartrip.com/");
	}

	public void clickHotelLink() {
		hotelLink.click();
	}

	public void sendLocalityTextBox(String locality) {
		localityTextBox.sendKeys(locality);
	}

	public void selectTravellerSection(String text) {
		new Select(travellerSelection).selectByVisibleText(text);
	}

	public void clickonSearch() {
		// TODO Auto-generated method stub
		searchButton.click();

	}
}
