package com.coding.uicontroller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectDropDown extends Wait {

	private WebElement element;

	public String selectElementByIndex(By locator, int index) throws Exception {
		String text = null;
		element = findElement(locator);
		if (element != null) {
			if (isElementClickable(element)) {
				Select selectElement = new Select(element);
				selectElement.selectByIndex(index);
				text = selectElement.getFirstSelectedOption().getText();
			} else {
				Loggers.error("Select Element not Clickable Exception ");
				throw new Exception("Element not Clickable Exception after wait for 20 sec ");
			}
		} else {
			Loggers.error("Select dropDown Web Element Not Located : " + locator);
			throw new Exception("Select dropDown Web Element Not Located : " + locator);
		}
		return text;
	}

	public void selectElementByText(By locator, String text) throws Exception {
		element = findElement(locator);
		if (element != null) {
			if (isElementClickable(element)) {
				Select selectElement = new Select(element);
				selectElement.selectByVisibleText(text);
			} else {
				Loggers.error("Select Element not Clickable Exception ");
				throw new Exception("Element not Clickable Exception after wait for 20 sec ");
			}

		} else {
			Loggers.error("Select dropDown Web Element Not Located : " + locator);
			throw new Exception("Select dropDown Web Element Not Located : " + locator);
		}
	}
}
