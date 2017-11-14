package com.codetest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResutPage extends BasePage {

	public SearchResutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		
	}
	
	//==============WebElements=======================
	
	@FindBy(xpath = "//section[@id='area']")
    private WebElement SectionArea;
	

	//=================Actions======================
	
	public boolean checktitle(String title)
	{
		System.out.println(driver.getTitle());
		return driver.getTitle().contains(title);
		
	}

	

}
