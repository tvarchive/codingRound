package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
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

	public static void explicitWaitForElement(WebElement element, long timeout, WebDriver driver) {

		WebDriverWait owait = new WebDriverWait(driver, timeout);
		owait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void clickOnElement(WebElement webelement) {

		try {

			webelement.click();
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	public static void switchToFrame(int index, WebDriver driver) {
		try {
			driver.switchTo().frame(index);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	public static void enterOntoElement(WebElement webelement, String textVal) {

		try {

			webelement.clear();
			webelement.sendKeys(textVal);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	public static void selectElementOnDropdown(WebElement webelement, String dropDownValue) {
		try {

			Select select = new Select(webelement);
			select.selectByVisibleText(dropDownValue);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	public static void fluentWaitForelementLocator(By locator, long timeout, int pollingFrequency, WebDriver driver) {
		Wait<WebDriver> newwait = new FluentWait<>(driver).withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(pollingFrequency, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		newwait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public static boolean isElementPresent(By by, WebDriver driver) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void calendarHandling(String date, WebDriver driver) {
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
							} catch (NoSuchElementException | ElementNotVisibleException e) {
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

	public static int getMonth(String monthreturned) {

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
