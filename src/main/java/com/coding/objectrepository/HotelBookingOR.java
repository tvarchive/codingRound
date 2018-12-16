package com.coding.objectrepository;

import org.openqa.selenium.By;

public class HotelBookingOR {
	public static final By hotelLink = By.linkText("Hotels");
	public static final By labelHotelSearch = By.cssSelector("div.searchForm>h1");
	public static final By localityTextBox = By.cssSelector("input#Tags");
	public static final By fromDropDownList = By.cssSelector("ul#ui-id-1>li>a");
	public static final By travellerSelection = By.cssSelector("select#travellersOnhome");
	public static final By searchButton = By.cssSelector("input#SearchHotelsButton");
	public static final By searchSummary = By.className("searchSummary");
}
