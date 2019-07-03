package com.codinground.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.codinground.uicommon.UiCommonLibrary;

public class SignInPage extends LoadableComponent<SignInPage>{
	
	private WebDriver driver;
	private static WebElement signInBtn;
	private static UiCommonLibrary objCommon;
	
	private static final By LOCATOR_SIGNIN_BUTTON = By.id("signInButton");
	private static final By LOCATOR_ERRORS = By.id("errors1");
	
	
	public SignInPage(WebDriver driver) {
	    this.driver=driver;
	    objCommon = new UiCommonLibrary(driver);
	    
	}
	
	public SignInPage clickSignIn() {
		
		objCommon.clickElement(LOCATOR_SIGNIN_BUTTON);
		return this;
	}
	
	public boolean verifyErrorMsg() {
		
		boolean flag = false;
		if(driver.findElement(By.id("errors1")).getText().contains("There were errors in your submission")) {
			flag = true;
		}
		return flag;
	}

	@Override
	protected void load() {
		signInBtn =driver.findElement(By.id("signInButton")); 
		
	}

	@Override
	protected void isLoaded() throws Error {
		
		Assert.assertTrue(driver.findElement(By.id("signInButton")).isDisplayed());
		
	}

}
