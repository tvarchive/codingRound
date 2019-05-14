import com.testvagrant.utils.ApplicationLibrary;
import com.testvagrant.utils.CustomLogger;
import com.testvagrant.utils.MiscConstants;
import com.testvagrant.utils.PublicLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlightBookingTest {

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
	public void testThatResultsAppearForAOneWayJourney() {

		driver.get("https://www.cleartrip.com/");

		// Selecting Jouney Type as One Way
		By byLocJourneyType = library.getByObject("journeyTypeSelection");
		library.waitForElementToDisplay(byLocJourneyType);
		driver.findElement(byLocJourneyType).click();

		// Entering From Location of the journey
		By fromLocation = library.getByObject("fromLocation");
		WebElement elemFromLocation = driver.findElement(fromLocation);
		elemFromLocation.clear();
		elemFromLocation.sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin
		By fromLocSuggestion = library.getByObject("fromLocationSuggestion");
		library.waitForElementToDisplay(fromLocSuggestion);

		// select the first item from the origin auto complete list
		driver.findElement(fromLocSuggestion).click();

		// Entering To Location of the journey
		By byLocToLocation = library.getByObject("toLocation");
		WebElement elemToLocation = driver.findElement(byLocToLocation);
		elemToLocation.clear();
		elemToLocation.sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination
		By byLocToSuggestion = library.getByObject("toLocationSuggestion");
		library.waitForElementToDisplay(byLocToSuggestion);

		// select the first item from the destination auto complete list
		driver.findElement(byLocToSuggestion).click();

		// Selecting date from date picker
		library.selectDateInDatePicker(4);

		// all fields filled in. Now click on search
		driver.findElement(library.getByObject("searchBtn")).click();

		// verify that result appears for the provided journey search
		By byLocSearchSummary = library.getByObject("searchSummary");

		Assert.assertTrue(library.isElementPresent(byLocSearchSummary));

	}

}
