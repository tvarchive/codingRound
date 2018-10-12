package com.test.testcases;

import com.sun.javafx.PlatformUtil;
import com.test.services.CommonUtils;
import com.test.services.ServiceInitializer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;

public class FlightBookingTest {

	static WebDriver driver = null;
	CommonUtils commonutils = new CommonUtils();
	WebDriverWait wait;

	@BeforeSuite
	public void setup() {
		System.out.println("Starting Setup");
		ServiceInitializer service = new ServiceInitializer();
		driver = service.getDriver();
		wait = new WebDriverWait(driver, 10);
		System.out.println("Setup Completed");
	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		driver.findElement(By.id("OneWay")).click();
		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin
		commonutils.waitFor(2000);

		WebElement originOptionsDropDown = driver.findElement(By.id("ui-id-1"));
		wait.until(ExpectedConditions.visibilityOf(originOptionsDropDown));
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination
		commonutils.waitFor(2000);

		// select the first item from the destination auto complete list
		WebElement destinationOptionsDropDown = driver.findElement(By.id("ui-id-2"));
		wait.until(ExpectedConditions.visibilityOf(destinationOptionsDropDown));
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		WebElement searchSummary = driver.findElement(By.className("searchSummary"));
		wait.until(ExpectedConditions.visibilityOf(searchSummary));
		// verify that result appears for the provided journey search
		Assert.assertTrue(commonutils.isElementPresent(driver, By.className("searchSummary")));

	}

	@AfterSuite
	public void closeBrowser() {
		// close the browser
		driver.quit();
	}

}
