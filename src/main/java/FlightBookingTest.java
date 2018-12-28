
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.TestBase;
import utils.CommonFunctions;

public class FlightBookingTest extends TestBase {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		WebElement oneway = driver.findElement(By.id("OneWay"));
		CommonFunctions.explicitWaitForElement(oneway, 50, driver);

		CommonFunctions.clickOnElement(oneway);

		WebElement fromtag = driver.findElement(By.id("FromTag"));
		CommonFunctions.enterOntoElement(fromtag, "Bangalore");

		By locatorofLoadedFromCity = By.xpath("//ul[@id='ui-id-1']/li/a");

		// wait for the auto complete options to appear for the origin
		CommonFunctions.fluentWaitForelementLocator(locatorofLoadedFromCity, 200, 2, driver);

		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

		WebElement totag = driver.findElement(By.id("ToTag"));
		CommonFunctions.enterOntoElement(totag, "Delhi");

		By locatorofLoadedDestinationCity = By.xpath("//ul[@id='ui-id-2']/li/a");
		// wait for the auto complete options to appear for the destination
		CommonFunctions.fluentWaitForelementLocator(locatorofLoadedDestinationCity, 200, 2, driver);
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		// selecting date on the calendar
		CommonFunctions.calendarHandling("02/03/2019", driver);
		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		Assert.assertTrue(CommonFunctions.isElementPresent(By.className("searchSummary"), driver));

	}

}
