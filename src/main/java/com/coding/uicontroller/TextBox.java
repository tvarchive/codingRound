package com.coding.uicontroller;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TextBox extends Wait {
	private WebElement element;

	public void setTextValue(By locator, String data) throws Exception {
		element = findElement(locator);
		if (element != null) {
			if (isElementClickable(element)) {
				element.clear();
				element.sendKeys(data);
			} else {
				Loggers.error("Element not Clickable Exception ");
				throw new Exception("Element not Clickable Exception after wait for 20 sec ");
			}
		} else {
			Loggers.error("Input textBox Web Element Not Located : " + locator);
			throw new Exception("Click Button Web Element Not Located : " + locator);
		}
	}

	public String getTextValue(By locator) throws Exception {
		String text = null;
		element = findElement(locator);
		if (element != null) {
			text = element.getText();
		} else {
			Loggers.error("Get text Web Element Not Located : " + locator);
			throw new Exception("Get text Web Element Not Located : " + locator);
		}
		return text;
	}
}
