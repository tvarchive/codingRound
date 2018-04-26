package com.resources;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver extends Utility{
	
	public boolean clickOnObject(By locator) {
		try {
			driver.findElement(locator).click();;
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
		
	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
	public static void waitForElementVisible(WebDriver driver,By locator){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
