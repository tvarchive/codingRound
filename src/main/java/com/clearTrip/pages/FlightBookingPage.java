package com.clearTrip.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeTest;
import com.clearTrip.utils.DriverFactoy;


public class FlightBookingPage extends DriverFactoy {

	public static WebElement searchSummary() {
		return getDriver().findElement(By.className("searchSummary"));
		
	}
	
	public  static void clickoneWay() {
		getDriver().findElement(By.id("OneWay")).click();

	}

	public static void fillFromField(String fromCity) {
		getDriver().findElement(By.id("FromTag")).clear();
		getDriver().findElement(By.id("FromTag")).sendKeys(fromCity);		

		List<WebElement> originOptions = getDriver().findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

	}

	public static void fillToField(String toCity) {
		// TODO Auto-generated method stub
		getDriver().findElement(By.cssSelector("#ToTag")).clear();
		getDriver().findElement(By.cssSelector("#ToTag")).sendKeys(toCity);
	
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = getDriver().findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

	}

	public static void clickOnSearch() {
		// TODO Auto-generated method stub
		getDriver().findElement(By.id("SearchBtn")).click();

	}

	public static void pickDate() {
		// TODO Auto-generated method stub
		getDriver().findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
	}
}
