package com.coding.objectrepository;

import org.openqa.selenium.By;

public class FlightBookingOR {
	public static final By flightLink = By.linkText("Flights");
	public static final By labelFlightSearch = By.cssSelector("form#SearchForm>h1");
	public static final By OneWay = By.cssSelector("input#OneWay");
	public static final By inputFrom = By.cssSelector("input#FromTag");
	public static final By fromDropDown = By.cssSelector("ul#ui-id-1");
	public static final By fromDropDownList = By.cssSelector("ul#ui-id-1>li>a");
	public static final By inputTo = By.cssSelector("input#ToTag");
	public static final By toDropDown = By.cssSelector("ul#ui-id-2");
	public static final By toDropDownList = By.cssSelector("ul#ui-id-2>li>a");
	public static final By inputDepartDate = By.cssSelector("input#DepartDate");
	public static final By datePicker = By.cssSelector("div#ui-datepicker-div");
	public static final By selectDate = By.xpath("//div[@id='ui-datepicker-div']//tbody//td[@data-year='2019']");
	public static final By btnSearch = By.cssSelector("input#SearchBtn");
	public static final By searchSummary = By.className("searchSummary");
}
