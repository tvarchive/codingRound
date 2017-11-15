package com.codetest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage 
{
	protected WebDriver driver;
	public BasePage (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//================WebElement===================
	@FindBy(linkText = "Hotels")
    private WebElement hotelLink;
	
	//================Actions======================
	public HotelPage clickOnHotel()
	{
		hotelLink.click();		
		return new HotelPage(driver);
		
	}

}
