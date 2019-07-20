package clearTrip.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import clearTrip.coreFramework.BaseTest;
import clearTrip.coreFramework.Utility;

public class FlightBookingPage extends BaseTest {

	private By tripType = By.id("OneWay");
//	private WebElement tripType = driver.findElement(By.id("OneWay"));

//	private WebElement sourceAirport = driver.findElement(By.id("FromTag"));
	private By sourceAirport = By.id("FromTag");

	private By destinationAirport = By.id("ToTag");

	private By departureDate = By.id("DepartDate");
	private By submitButton = By.id("SearchBtn");

	public FlightBookingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void setSourceAirport(String sourceAirport) {

		driver.findElement(this.sourceAirport).clear();
		driver.findElement(this.sourceAirport).sendKeys(sourceAirport);

		// wait for the auto complete options to appear for the origin
		// instead of hard stop at our tests, let's wait logically
		Utility.waitUntilExpectedConditions(
				ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-1"), By.tagName("li")),
				"check your source airport city");

		// select the first item from the origin auto complete list
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

	}

	public void setDestinationAirport(String destinationAirport) {
		driver.findElement(this.destinationAirport).clear();
		driver.findElement(this.destinationAirport).sendKeys(destinationAirport);

		// wait for the auto complete options to appear for the destination
		// instead of hard stop at our tests, let's wait logically
		Utility.waitUntilExpectedConditions(
				ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-2"), By.tagName("li")),
				"check your destination airport city");

		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();
	}

	public void setDepartureDate(String departureDate) {

		driver.findElement(this.departureDate).clear();
		driver.findElement(this.departureDate).sendKeys(departureDate + Keys.TAB);

	}

	public void setTripType(String tripType) {
		driver.findElement(this.tripType).click();
	}

	public void waitForFlightResultsReady() {
		// TODO Auto-generated method stub

		// wait for flight results to be ready
		// instead of hard stop at our tests, let's wait logically
		Utility.waitUntilExpectedConditions(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Your search results are ready.']")),
				"Searching flight not completed in 20 seconds");

	}

	public void clickSubmit() {
		// all fields filled in. Now click on search
		driver.findElement(this.submitButton).click();

	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

}
