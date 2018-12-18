package com.clearTrip.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

import com.clearTrip.utils.DriverFactoy;

public class HotelBookingPage extends DriverFactoy {
	
	public static void clickHotelLink() {
		getDriver().findElement(By.cssSelector("a[title='Find hotels in destinations around the world'] > span.productIcon.hotels")).click();
	}

	public static void sendLocalityTextBox(String locality) {
		getDriver().findElement(By.id("Tags")).sendKeys(locality);
		
	}

	public static void selectTravellerSection(String text) {
		WebElement tavellerSelection=driver.findElement(By.id("travellersOnhome"));
		tavellerSelection.click();
		new Select(tavellerSelection).selectByVisibleText(text);
	}

	public static void clickonSearch() {
		// TODO Auto-generated method stub
		getDriver().findElement(By.id("SearchHotelsButton")).click();

	}
}
