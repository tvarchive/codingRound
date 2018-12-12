import com.sun.javafx.PlatformUtil;

import PageObjects.FlightBookingPageObject;
import Utils.DriverFactoy;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends FlightBookingPageObject {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		String toCity = "Bangalore";
		String fromCity = "Delhi";

		clickoneWay();
		fillFromField(toCity);
		fillToField(fromCity);
		pickDate();

		// all fields filled in. Now click on search
		clickOnSearch();

		waitFor(5000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

	}

}
