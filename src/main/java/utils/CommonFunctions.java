package utils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jna.Platform;

public class CommonFunctions {

	public static void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

// For setting the Driver Path to initialize the driver
	public static void setDriverPath() {
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

//wait for particular element to be clicked
	public static void explicitWaitForElement(WebElement element, long timeout, WebDriver driver) {

		WebDriverWait owait = new WebDriverWait(driver, timeout);
		owait.until(ExpectedConditions.elementToBeClickable(element));

	}

//click on WebElement
	public static void clickOnElement(WebElement webelement) {

		try {

			webelement.click();
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

// to switch between frames
	public static void switchToFrame(int index, WebDriver driver) {
		try {
			driver.switchTo().frame(index);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

//Enter into elements with given inputs
	public static void enterOntoElement(WebElement webelement, String textVal) {

		try {

			webelement.clear();
			webelement.sendKeys(textVal);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

//select element from dropdown values
	public static void selectElementOnDropdown(WebElement webelement, String dropDownValue) {
		try {

			Select select = new Select(webelement);
			select.selectByVisibleText(dropDownValue);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

//wait for element to appear specifically used here for AJAX element to load 
	public static void fluentWaitForelementLocator(By locator, long timeout, int pollingFrequency, WebDriver driver) {
		Wait<WebDriver> newwait = new FluentWait<>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(pollingFrequency, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		newwait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

//verification for element to present
	public static boolean isElementPresent(By by, WebDriver driver) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

//method to handle calendar dates as per user input
	public static void calendarHandling(String date, WebDriver driver) {

		// elements for clicking calendar buttons
		String PrevButtonM = "//div[@id='ui-datepicker-div']/descendant::div[@class='header']/a[@title='Prev']";
		String NextButtonM = "//div[@id='ui-datepicker-div']/descendant::div[@class='header']/a[@title='Next']";
		String currentReturnedYear = "//div[@id='ui-datepicker-div']/descendant::div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-year']";
		String returnedMonth = "//div[@id='ui-datepicker-div']/descendant::div[@class='monthBlock first']//div[@class='title']/span[@class='ui-datepicker-month']";

		date = date.trim();
		String DateArr[] = date.split("/");
		int Day = Integer.parseInt(DateArr[0]);
		int month = Integer.parseInt(DateArr[1]);
		int Year = Integer.parseInt(DateArr[2]);

		HashMap<String, Integer> calendarMonths = new HashMap<String, Integer>();

		calendarMonths.put("January", 1);
		calendarMonths.put("February", 2);
		calendarMonths.put("March", 3);
		calendarMonths.put("April", 4);
		calendarMonths.put("June", 6);
		calendarMonths.put("July", 7);
		calendarMonths.put("August", 8);
		calendarMonths.put("September", 9);
		calendarMonths.put("October", 10);
		calendarMonths.put("November", 11);
		calendarMonths.put("December", 12);

		int retYear;

		try {
			System.out.println(Day + "-" + month + "-" + Year);
			do {
// return the Year displaying on the application calendar
				retYear = Integer.parseInt(driver.findElement(By.xpath(currentReturnedYear)).getText());

				if (retYear < Year) {
//taking calendar to year provided by user
					driver.findElement(By.xpath(NextButtonM)).click();

				}
				if (retYear > Year) {
//year less than current year displayed on calendar can be propagated
					throw new RuntimeException("Year cannot be less than current year");

				}

// If same checking for the date input by user and comparing with that of
// calendar display

				int retmonthcount = 0;
				if (retYear == Year) {
					for (int i = 1; i <= 12; i++) {

// fetching current calendar month displayed
						String returnedmonth = driver.findElement(By.xpath(returnedMonth)).getText();
						if (calendarMonths.containsKey(returnedmonth))
							retmonthcount = calendarMonths.get(returnedmonth);

// propagating to future months as per given input
						if ((retmonthcount < month) && (driver.findElement(By.xpath(NextButtonM)).isDisplayed())) {

							driver.findElement(By.xpath(NextButtonM)).click();

						}
//propagating to earlier months as per provided input
						if ((retmonthcount > month) && (driver.findElement(By.xpath(PrevButtonM))).isDisplayed()) {

							driver.findElement(By.xpath(PrevButtonM)).click();

						} else if (retmonthcount == month) {
							try {
								driver.findElement(By.xpath(
										"//div[@id='ui-datepicker-div']/descendant::div[@class='monthBlock first']//a[text()='"
												+ Day + "']"))
										.click();
								break;

							} catch (Exception e) {
								throw new Exception("You may have entered a wrong date");
							}
						}
					}

				}

			} while (retYear != Year);

		} catch (Throwable e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			// System.out.println("Invalid input Date as per Site Calendar");
			// System.out.println());
		}
	}

}
