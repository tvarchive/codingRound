
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import testbase.TestBase;
import utils.CommonFunctions;

public class HotelBookingTest extends TestBase {

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@Test
	public void shouldBeAbleToSearchForHotels() {

		PageFactory.initElements(driver, this);

		CommonFunctions.clickOnElement(hotelLink);

		CommonFunctions.enterOntoElement(localityTextBox, "Indiranagar, Bangalore");

		CommonFunctions.selectElementOnDropdown(travellerSelection, "1 room, 2 adults");

		CommonFunctions.clickOnElement(searchButton);

	}

}
