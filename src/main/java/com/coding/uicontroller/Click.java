package com.coding.uicontroller;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Click extends Wait {

	private WebElement element;

	public void buttonClick(By locator) throws Exception {
		element = findElement(locator);
		if (element != null) {
			if (isElementClickable(element))
				element.click();
		} else {
			Loggers.error("Click Button Web Element Not Located : " + locator);
			throw new Exception("Click Button Web Element Not Located : " + locator);
		}
	}

	public void buttonClick(By locator, int index) throws Exception {
		List<WebElement> list = findElements(locator);
		if (list != null) {
			if (isElementClickable(list.get(index)))
				list.get(index).click();
			else
				Loggers.error("Cannot click on list index " + locator);
		} else {
			Loggers.error("Click Button Web Element Not Located : " + locator);
			throw new Exception("Click Button Web Element Not Located : " + locator);
		}
	}
}
