package com.clearTrip.Tests;
import com.clearTrip.pages.HotelBookingPage;
import com.clearTrip.utils.DriverFactoy;
import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends HotelBookingPage {

	@Test
	public void shouldBeAbleToSearchForHotels() {
		String locality = "Indiranagar, Bangalore";
		String travellerSection = "1 room, 2 adults";

		HotelBookingPage.clickHotelLink();
		HotelBookingPage.sendLocalityTextBox(locality);
		HotelBookingPage.selectTravellerSection(travellerSection);
		HotelBookingPage.clickonSearch();
	}

}
