package com.BusinessFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.resources.ActionDriver;

public class FlightsPage extends ActionDriver {
	
	public void selectValueAjax(String textboxID, String dropdownID, String searchValue){
		try {
			waitForElementVisible(driver, By.id(textboxID));
			driver.findElement(By.id(textboxID)).clear();
			driver.findElement(By.id(textboxID)).sendKeys(searchValue);

			//wait for the auto complete options to appear for the origin
			waitForElementVisible(driver, By.id(dropdownID));
			
			List<WebElement> originOptions = driver.findElement(By.id(dropdownID)).findElements(By.tagName("li"));
			originOptions.get(0).click();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
