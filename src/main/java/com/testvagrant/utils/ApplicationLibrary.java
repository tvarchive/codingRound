package com.testvagrant.utils;

import java.time.LocalDate;

import org.openqa.selenium.By;

public class ApplicationLibrary extends PublicLibrary {

	/**
	 * Selects a date after mentioned number of days in date picker
	 * 
	 * @param numOfDaysAfter : number of days from today
	 */
	public void selectDateInDatePicker(int numOfDaysAfter) {
		LocalDate todayDate = LocalDate.now();
		LocalDate targetdate = todayDate.plusDays(numOfDaysAfter);
		waitFor(500);
		int dayOfTargetDate = targetdate.getDayOfMonth();
		waitForElementToDisplay(
				By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody//a[text()='" + dayOfTargetDate + "']"));
		getDriverInstance()
				.findElement(
						By.xpath("//*[@id='ui-datepicker-div']/div/table/tbody//a[text()='" + dayOfTargetDate + "']"))
				.click();
	}
}
