package com.coding.uicontroller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VerifyWebElement extends Wait {
	private WebElement element;

	public boolean isElementPresent(By locator) {
		boolean isElement = false;
		element = findElement(locator);
		if (element != null) {
			if (element.isDisplayed())
				isElement = true;
		} else {
			isElement = false;
		}
		return isElement;
	}

	public boolean isElementSelected(By locator) throws Exception {
		boolean isElement = false;
		element = findElement(locator);
		if (element != null) {
			if (element.isSelected()) {
				isElement = true;
			}
		} else {
			isElement = false;
		}
		return isElement;
	}

	public boolean isElementEnabled(By locator) throws Exception {
		boolean isElement = false;
		element = findElement(locator);
		if (element != null) {
			if (element.isEnabled())
				isElement = true;
		} else {
			isElement = false;
		}
		return isElement;
	}
}
