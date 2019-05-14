import com.testvagrant.utils.TestCaseTemplate;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightBookingTest extends TestCaseTemplate {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		// Selecting Jouney Type as One Way
		logger.log("Selecting Jouney Type as One Way");
		library.clickElement(library.getByObject("journeyTypeSelection"));

		// Entering From Location of the journey
		logger.log("Entering From Location of the journey");
		library.setTextToElement(library.getByObject("fromLocation"), exceldata.get("OriginLocation"));

		// selecting the first item from the origin auto complete list"
		logger.log("selecting the first item from the origin auto complete list");
		library.clickElement(library.getByObject("fromLocationSuggestion"));

		// Entering To Location of the journey
		logger.log("Entering To Location of the journey");
		library.setTextToElement(library.getByObject("toLocation"), exceldata.get("DestinationLocation"));

		// select the first item from the destination auto complete list
		logger.log("select the first item from the destination auto complete list");
		library.clickElement(library.getByObject("toLocationSuggestion"));

		// Selecting date 4 days after today from date picker
		logger.log("Selecting date 4 days after today from date picker");
		library.selectDateInDatePicker(4);

		// All fields filled in. Now click on search
		logger.log("All fields filled in. Now click on search");
		library.clickElement(library.getByObject("searchBtn"));

		// Verify that result appears for the provided journey search
		logger.log("Verify that result appears for the provided journey search");
		By byLocSearchSummary = library.getByObject("searchSummary");
		library.waitForElementToDisplay(byLocSearchSummary);
		Assert.assertTrue(library.isElementPresent(byLocSearchSummary));

	}

}
