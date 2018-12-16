package com.coding.pages;

import com.coding.base.BaseExecution;
import com.coding.objectrepository.HotelBookingOR;
import com.coding.uicontroller.Click;
import com.coding.uicontroller.Loggers;
import com.coding.uicontroller.SelectDropDown;
import com.coding.uicontroller.TextBox;
import com.coding.uicontroller.Wait;

public class HotelBookingPage extends BaseExecution {
	Click click = new Click();
	TextBox textbox = new TextBox();
	Wait wait = new Wait();
	SelectDropDown select = new SelectDropDown();

	public void fillDetailsHotelBooking() throws Exception {
		click.buttonClick(HotelBookingOR.hotelLink);
		wait.waitforPageLoad();
		if (wait.findElement(HotelBookingOR.labelHotelSearch)!=null) {
			assertEqual(textbox.getTextValue(HotelBookingOR.labelHotelSearch),
					getProperties().getProperty("labelHotelSearch"));
		}
		textbox.setTextValue(HotelBookingOR.localityTextBox, getProperties().getProperty("hotellocation"));
		Thread.sleep(2000);
		click.buttonClick(HotelBookingOR.fromDropDownList, 0);
		select.selectElementByText(HotelBookingOR.travellerSelection, getProperties().getProperty("travellerCount"));
		click.buttonClick(HotelBookingOR.searchButton);
		wait.waitForElementToDisplay(HotelBookingOR.searchSummary);
		if (wait.findElement(HotelBookingOR.searchSummary)!=null) {
			assertEqual(wait.findElement(HotelBookingOR.searchSummary).isDisplayed(), true);
			Loggers.info("Seach Summary is Present");
		}
	}
}
