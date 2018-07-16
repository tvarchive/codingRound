package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.DriverInit;
import helpers.ElementFinder;

import java.util.List;

public class FlightBookingTest {

	@Test(alwaysRun=true, testName="1WayFlightSearch")
	public void testCaseInitialization() throws InterruptedException {
		helpers.Log.startTestCase("TC_02" + ":" + "1WayFlightSearch");
		DriverInit.setDriver();
		DriverInit.driverInit("https://www.cleartrip.com/");
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "OneWay")).click();
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "FromTag")).click();
		DriverInit.getDriver().findElement(ElementFinder.getByType("name", "origin")).sendKeys("Bangalore");
		List<WebElement> originOptions = DriverInit.getDriver().findElement(ElementFinder.getByType("id", "ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "ToTag")).clear();
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "ToTag")).sendKeys("Delhi");  
		//select the first item from the destination auto complete list
		List<WebElement> destinationOptions = DriverInit.getDriver().findElement(ElementFinder.getByType("id", "ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();
		DriverInit.getDriver().findElement(ElementFinder.getByType("xpath", "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[1]/a")).click();

		//All fields validated. Clicking on search 
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "SearchBtn")).click();
		//verify that result appears for the provided journey search
		WebElement result = DriverInit.getDriver().findElement(ElementFinder.getByType("classname", "searchSummary"));
		Assert.assertTrue(result.isDisplayed());
		DriverInit.driverClose();
		helpers.Log.endTestCase("FlightBookingTest");
	}
}