import com.testvagrant.pageobjects.HotelBookingPage;
import com.testvagrant.utils.ApplicationLibrary;
import com.testvagrant.utils.CustomLogger;
import com.testvagrant.utils.MiscConstants;
import com.testvagrant.utils.PublicLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

	WebDriver driver;
	ApplicationLibrary library;
	CustomLogger logger;

	@BeforeTest
	public void setUpTestObjects() {
		logger = new CustomLogger();
		logger.log("Executing test setup");
		library = new ApplicationLibrary();
		library.initialiseBrowser(MiscConstants.BROWSER_CHROME);
		this.driver = library.getDriverInstance();
	}

	@AfterTest
	public void closingBrowserWindow() {
		logger.log("Executing test clean up");
		library.closeWebBrowser();
	}

	@Test
	public void shouldBeAbleToSearchForHotels() {
		HotelBookingPage bookingpage = PageFactory.initElements(driver, HotelBookingPage.class);

		driver.get("https://www.cleartrip.com/");
		bookingpage.hotelLink.click();

		bookingpage.localityTextBox.sendKeys("Indiranagar, Bangalore");

		By holtelLocationSuggestion = library.getByObject("holtelLocationSuggestion");
		library.waitForElementToDisplay(holtelLocationSuggestion);

		// select the first item from the hotel auto complete list
		driver.findElement(holtelLocationSuggestion).click();
		
		library.selectDateInDatePicker(1);
		library.selectDateInDatePicker(2);

		new Select(bookingpage.travellerSelection).selectByVisibleText("1 room, 2 adults");
		bookingpage.searchButton.click();

		// verify that result appears for the provided journey search
		By byLocSearchSummary = library.getByObject("searchSummary");
		
		Assert.assertTrue(library.isElementPresent(byLocSearchSummary));
		System.out.println("manoj");
	}

}
