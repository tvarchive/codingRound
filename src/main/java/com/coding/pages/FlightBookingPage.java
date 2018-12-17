package com.coding.pages;

import com.coding.base.BaseExecution;
import com.coding.objectrepository.FlightBookingOR;
import com.coding.uicontroller.Click;
import com.coding.uicontroller.Loggers;
import com.coding.uicontroller.TextBox;
import com.coding.uicontroller.Wait;

public class FlightBookingPage extends BaseExecution {
	Click click = new Click();
	TextBox textbox = new TextBox();
	Wait wait = new Wait();

	public void fillDetailsSearchFlight() throws Exception {
		click.buttonClick(FlightBookingOR.flightLink);
		wait.waitforPageLoad();
		if (wait.findElement(FlightBookingOR.labelFlightSearch) != null) {
			assertEqual(textbox.getTextValue(FlightBookingOR.labelFlightSearch),
					getProperties().getProperty("labelFlightSearch"));
		}
		click.buttonClick(FlightBookingOR.OneWay);
		textbox.setTextValue(FlightBookingOR.inputFrom, getProperties().getProperty("flightFrom"));
		Thread.sleep(4000);
		click.buttonClick(FlightBookingOR.fromDropDownList, 0);
		textbox.setTextValue(FlightBookingOR.inputTo, getProperties().getProperty("flightTo"));
		Thread.sleep(4000);
		click.buttonClick(FlightBookingOR.toDropDownList, 0);
		click.buttonClick(FlightBookingOR.inputDepartDate);
		wait.waitForElementToDisplay(FlightBookingOR.datePicker);
		click.buttonClick(FlightBookingOR.selectDate, 2);
		click.buttonClick(FlightBookingOR.btnSearch);
		wait.waitForElementToDisplay(FlightBookingOR.searchSummary);
		assertEqual(wait.findElement(FlightBookingOR.searchSummary).isDisplayed(), true);
		Loggers.info("Seach Summary is Present");
	}
}
