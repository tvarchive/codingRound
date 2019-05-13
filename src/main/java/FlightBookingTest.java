import com.testvagrant.utils.ApplicationLibrary;
import com.testvagrant.utils.PublicLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

	WebDriver driver;
	PublicLibrary library;
	
	@BeforeTest
	public void setUpTestObjects() {
		System.out.println("before executed");
		library = new ApplicationLibrary();
		library.initialiseBrowser("chrome");
		this.driver = library.getDriverInstance();
	}
	
	@AfterTest
	public void closingBrowserWindow() {
		library.closeWebBrowser();
	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		driver.get("https://www.cleartrip.com/");
		library.waitFor(2000);
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin

		library.waitFor(5000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

		driver.findElement(By.id("toTag")).clear();
		driver.findElement(By.id("toTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		library.waitFor(2000);
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		library.waitFor(5000);

		// verify that result appears for the provided journey search
		Assert.assertTrue(library.isElementPresent(By.className("searchSummary")));
		
		
	}

	




}
