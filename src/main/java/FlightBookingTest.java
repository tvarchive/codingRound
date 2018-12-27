
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jna.Platform;

public class FlightBookingTest {

	WebDriver driver;
	WebDriverWait oWait;
	ChromeOptions options;

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		setDriverPath();
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.cleartrip.com/");
		oWait = new WebDriverWait(driver, 100);
		oWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("OneWay"))));
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin
		Wait<WebDriver> newwait = new FluentWait<>(driver).withTimeout(200, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		newwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='ui-id-1']/li/a")));
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));

		System.out.println(originOptions);

		originOptions.get(0).click();

		oWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ToTag' and @name='destination']")));
		driver.findElement(By.xpath("//input[@id='ToTag' and @name='destination']")).clear();
		driver.findElement(By.xpath("//input[@id='ToTag' and @name='destination']")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination
		newwait = new FluentWait<>(driver).withTimeout(200, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		newwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='ui-id-2']/li/a")));

		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		calendarHandling("27/12/2018");
		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

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

	public void calendarHandling(String date) {
		String PrevButtonM = "//div[@id='ui-datepicker-div']/descendant::div[@class='header']/a[@title='Prev']";
		String NextButtonM = "//div[@id='ui-datepicker-div']/descendant::div[@class='header']/a[@title='Next']";
		String currentReturnedYear = "//div[@id='ui-datepicker-div']/descendant::div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-year']";
		String returnedMonth = "//div[@id='ui-datepicker-div']/descendant::div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-month']";

		date = date.trim();
		String DateArr[] = date.split("/");
		int Day = Integer.parseInt(DateArr[0]);
		int month = Integer.parseInt(DateArr[1]);
		int Year = Integer.parseInt(DateArr[2]);

		int retYear;

		try {
			System.out.println(Day + "-" + month + "-" + Year);
			do {
				retYear = Integer.parseInt(driver.findElement(By.xpath(currentReturnedYear)).getText());

				if (retYear < Year) {
					try {
						driver.findElement(By.xpath(NextButtonM)).click();
					} catch (ElementNotVisibleException e) {
						throw new Exception("Year cannot be more than current year or in negative");
					}
				}
				if (retYear > Year) {

					driver.findElement(By.xpath(PrevButtonM)).click();

				}
				if (retYear == Year) {
					for (int i = 1; i <= 12; i++) {
						String returnedmonth = driver.findElement(By.xpath(returnedMonth)).getText();
						int retmonthcount = getMonth(returnedmonth);

						if (retmonthcount < month) {

							driver.findElement(By.xpath(NextButtonM)).click();

						}
						if (retmonthcount > month) {

							driver.findElement(By.xpath(PrevButtonM)).click();

						} else if (retmonthcount == month) {
							try {
								driver.findElement(By.xpath(
										"//div[@id='ui-datepicker-div']/descendant::div[@class='monthBlock first']//a[contains(text(),'"
												+ Day + "')]"))
										.click();
							} catch (NoSuchElementException e) {
								throw new Exception("You may have entered a Sunday date or Invalid Date");
							}
						}
					}

				}

			} while (retYear != Year);

		} catch (Exception e) {
			// System.out.println("Invalid input Date");
			System.out.println(e.getMessage());
		}
	}

	public int getMonth(String monthreturned) {

		if (monthreturned.equalsIgnoreCase("January"))
			return 1;
		else if (monthreturned.equalsIgnoreCase("February"))
			return 2;
		else if (monthreturned.equalsIgnoreCase("March"))
			return 3;
		else if (monthreturned.equalsIgnoreCase("April"))
			return 4;
		else if (monthreturned.equalsIgnoreCase("May"))
			return 5;
		else if (monthreturned.equalsIgnoreCase("June"))
			return 6;
		else if (monthreturned.equalsIgnoreCase("July"))
			return 7;
		else if (monthreturned.equalsIgnoreCase("August"))
			return 8;
		else if (monthreturned.equalsIgnoreCase("September"))
			return 9;
		else if (monthreturned.equalsIgnoreCase("October"))
			return 10;
		else if (monthreturned.equalsIgnoreCase("November"))
			return 11;
		else if (monthreturned.equalsIgnoreCase("December"))
			return 12;

		else
			return -1;

	}
}
