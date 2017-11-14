package com.codetest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.codetest.helper.AjaxHelper;

public class HotelPage extends BasePage {

	public HotelPage(WebDriver driver) 
	{
		
		super(driver);
		this.driver=driver;
		
	}
	private Select select=null;
	
	//==============WebElements=======================
	
	@FindBy(id = "Tags")
    private WebElement localityTextBox;
	
	@FindBy(id = "ui-id-1")
    private WebElement dropdown;
	
	
	 @FindBy(id = "SearchHotelsButton")
	 private WebElement searchButton;
	 
	 @FindBy(id = "travellersOnhome")
	 private WebElement travellerSelection;
	
	//====================== Actions==================
	
	public void entertext(String text)
	{
		localityTextBox.clear();
		localityTextBox.sendKeys(text);
		AjaxHelper.select(driver, dropdown, "Indiranagar, Bangalore Karnataka, India");
		
	}
	
	public void selectdropdown(String text)
	{
		
		select = new Select(travellerSelection);
		select.selectByVisibleText(text);
		Reporter.log("selected given text");
	}
	
	public SearchResutPage clickonsearch()
	{
		searchButton.click();
		Reporter.log("clicked on search button");
		
		return new SearchResutPage(driver);
	}
	
	
	
	

}
