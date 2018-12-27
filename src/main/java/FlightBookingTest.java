
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jna.Platform;

import java.util.List;

public class FlightBookingTest {

	WebDriver driver;
	WebDriverWait oWait;

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		setDriverPath();
		driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");
		oWait = new WebDriverWait(driver, 100);
		oWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("OneWay"))));
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin
		waitFor(4000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));

		System.out.println(originOptions);

		originOptions.get(0).click();

		oWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ToTag' and @name='destination']")));
		driver.findElement(By.xpath("//input[@id='ToTag' and @name='destination']")).clear();
		driver.findElement(By.xpath("//input[@id='ToTag' and @name='destination']")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination
		waitFor(4000);
		oWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.tagName("li"))));

		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[5]/a")).click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		waitFor(5000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

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

	private void setDriverPath() {
		if (Platform.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (Platform.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (Platform.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}
}
