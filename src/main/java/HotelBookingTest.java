import com.testvagrant.pageobjects.HotelBookingPage;
import com.testvagrant.utils.TestCaseTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelBookingTest extends TestCaseTemplate {

	@Test
	public void shouldBeAbleToSearchForHotels() {
		HotelBookingPage bookingpage = PageFactory.initElements(driver, HotelBookingPage.class);

		// Clicking on Hotels section link
		logger.log("Clicking on Hotels section link");
		bookingpage.hotelLink.click();

		// Set text to Hotel location field
		logger.log("Set text to Hotel location field");
		bookingpage.localityTextBox.sendKeys(exceldata.get("HotelLocation"));

		// select the first item from the hotel auto complete list
		logger.log("select the first item from the hotel auto complete list");
		library.clickElement(library.getByObject("holtelLocationSuggestion"));

		// Selecting a date which is 1 day after today as Check-In date
		logger.log("Selecting a date which is 1 day after today as Check-In date");
		library.selectDateInDatePicker(1);

		// Selecting a date which is 2 days after today as Check-Out date
		logger.log("Selecting a date which is 2 days after today as Check-Out date");
		library.selectDateInDatePicker(2);

		// Selecting num of rooms and guests
		logger.log("Selecting num of rooms and guests");
		new Select(bookingpage.travellerSelection).selectByVisibleText(exceldata.get("RoomGuestsSpecification"));

		// Clicking on search button after entering details
		logger.log("Clicking on search button after entering details");
		bookingpage.searchButton.click();

		// verify that result appears for the provided journey search
		logger.log("verify that result appears for the provided journey search");
		By byLocSearchSummary = library.getByObject("searchSummary");
		library.waitForElementToDisplay(byLocSearchSummary);
		Assert.assertTrue(library.isElementPresent(byLocSearchSummary));
	}

}
