package com.codinground.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class SearchSummaryPage extends LoadableComponent<SearchSummaryPage>{

	
	private WebDriver driver;
	private static WebElement elemSearchSummary;
	private static String place;
	
	private static final By LOCATOR_TITLE_SEARCH_SUMMARY = By.className("searchSummary");
	private static final By LOCATOR_TXT_FROMTO_SEARCH_RESULTS = By.xpath("//*[@class='searchSummary']/*[1]");
	
	public SearchSummaryPage(WebDriver driver) {
		this.driver = driver;
	}
		public boolean verifyIfRelevant(String from, String to) {
		boolean flag = false;
		if(driver.findElement(LOCATOR_TXT_FROMTO_SEARCH_RESULTS).getText().contains(from)&&
				driver.findElement(LOCATOR_TXT_FROMTO_SEARCH_RESULTS).getText().contains(to)) {
			flag = true;
		}
		
		return flag;
	}
		
		public boolean verifyIfRelevant(String place) {
			boolean flag = false;
			if(driver.findElement(LOCATOR_TXT_FROMTO_SEARCH_RESULTS).getText().contains(place)) {
				flag = true;
			}
			
			return flag;
		}

	@Override
	protected void load() {
		
		elemSearchSummary = driver.findElement(LOCATOR_TITLE_SEARCH_SUMMARY);
	}

	@Override
	protected void isLoaded() throws Error {
		
		Assert.assertTrue(driver.findElement(LOCATOR_TITLE_SEARCH_SUMMARY).isDisplayed());
		
	}
	
	
}
