package com.codetest.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.codetest.helper.StartWebDriver;

public class WaitHelper extends StartWebDriver
{
	
	public static  WebElement customewait(WebDriver driver, By locator)
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	
	public static WebElement elementWait(WebDriver driver, WebElement ele)
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		element = wait.until(ExpectedConditions.elementToBeClickable(ele));
		return element;
	}
	
	
	public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
