package com.codoingRoiund.utils;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.codoingRound.BasePage.BaseObject;

public class pageRepo extends BaseObject{
	WebElement element;
		    
	    public WebElement hotels() {
	    	try {
				element = driver.findElement(By.xpath("//li[contains(@class,'hotelApp')]//a[contains(text(),'Hotels')]"));
			} catch (Exception e) {
				System.out.println("Hotels Icon not found");
			}
	    	return element;
	    }
	    
	    public WebElement hotelSearch() {
	    	try {
				element = driver.findElement(By.xpath("//input[@id='Tags']"));
			} catch (Exception e) {
				System.out.println("Hotels search Icon not found");
			}
	    	return element;
	    }
	    
	    public WebElement travellers() {
	    	try {
				element = driver.findElement(By.xpath("//select[@id='travellersOnhome']"));
			} catch (Exception e) {
				System.out.println("Travellers Icon not found");
			}
	    	return element;
	    }
	    
	    public WebElement searchHotels() {
	    	try {
				element = driver.findElement(By.xpath("//input[@id='SearchHotelsButton']"));
			} catch (Exception e) {
				System.out.println("Search Hotels Icon not found");
			}
	    	return element;
	    }

}
