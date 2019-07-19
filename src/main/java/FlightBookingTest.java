import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightBookingTest extends BaseTest {
	
	//we will be sending the data from FlightBookingTest.properties or yml file later so collating
	String flightFrom = "Bangalore";
	String flightTo = "Delhi";
	String travelDate = "";

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		//the driver config and launch has been taken care of in BaseTest class 
		
		LocalDate today = LocalDate.now();
		LocalDate todayAnd2 = today.plusDays(2);

		DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		travelDate = todayAnd2.format(d);

		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys(flightFrom);

		// wait for the auto complete options to appear for the origin
		//instead of hard stop at our tests, let's wait logically
		waitUntilExpectedConditions(
				ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-1"), By.tagName("li")),
				"check your source airport city");
		
		// select the first item from the origin auto complete list
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys(flightTo);

		// wait for the auto complete options to appear for the destination
		//instead of hard stop at our tests, let's wait logically
		waitUntilExpectedConditions(
				ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ui-id-2"), By.tagName("li")),
				"check your destination airport city");

		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		driver.findElement(By.id("DepartDate")).clear();
		driver.findElement(By.id("DepartDate")).sendKeys(travelDate + Keys.TAB);

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		//wait for flight results to be ready
		//instead of hard stop at our tests, let's wait logically
		waitUntilExpectedConditions(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Your search results are ready.']")),
				"Searching flight not completed in 20 seconds");

		// verify that result appears for the provided journey search
		Assert.assertTrue((driver.getTitle().toLowerCase()).contains(flightFrom.toLowerCase()));

	}

	//unused methods removed 
}
