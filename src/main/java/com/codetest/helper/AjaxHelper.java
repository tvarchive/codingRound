package com.codetest.helper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AjaxHelper extends StartWebDriver
{
	public static void select(WebDriver driver, By locator,String text)
	{
		 List<WebElement> originOptions = driver.findElement(locator).findElements(By.tagName("li"));
	        for(WebElement li : originOptions)
	        {
	        	if(li.getText().contains(text))
	        	{
	        		li.click();
	        		break;
	        	}
	        }
		
	}
	public static void select(WebDriver driver, WebElement ele,String text)
	{
		 List<WebElement> originOptions = ele.findElements(By.tagName("li"));
	        for(WebElement li : originOptions)
	        {
	        	if(li.getText().contains(text))
	        	{
	        		li.click();
	        		break;
	        	}
	        }
		
	}
}
