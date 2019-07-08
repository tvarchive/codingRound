import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.SetUp;

public class FlightBookingTest extends SetUp {

	@BeforeTest
	public void setUp() throws IOException {

		mysetUp();

		waitFor(2000);

	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() throws Exception {

		FlightBookingPage flightBookingPage = new FlightBookingPage(driver);

		flightBookingPage.selectOneWay();
		flightBookingPage.selectFromCity();

		// wait for the auto complete options to appear for the origin
		waitFor(3000);

		driver.findElement(flightBookingPage.fromField).sendKeys(Keys.ENTER);

		flightBookingPage.selectToCity();

		// wait for the auto complete options to appear for the destination
		waitFor(3000);

		// select the first item from the destination auto complete list
		flightBookingPage.selectFirstDestination();

		flightBookingPage.selectDatePicker();

		// all fields filled in. Now click on search
		flightBookingPage.clickOnSearch();

		waitFor(5000);

		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(flightBookingPage.searchSummary));

	}

	@AfterTest
	public void teatDown() {

		// close the browser
		driver.quit();

	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
