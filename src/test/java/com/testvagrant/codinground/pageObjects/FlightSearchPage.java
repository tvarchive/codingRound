package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightSearchPage extends BasePage{

	public FlightSearchPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//i[contains(text(),'Logout')]")
	private WebElement homePageElement;
	
	
}
