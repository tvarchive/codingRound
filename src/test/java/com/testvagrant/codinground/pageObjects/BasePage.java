package com.testvagrant.codinground.pageObjects;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.testvagrant.codinground.utilities.SeleniumUtilities;

import net.bytebuddy.matcher.ElementMatchers;

public class BasePage {


	WebDriver driver;
	private JavascriptExecutor js;
	SeleniumUtilities wait;

	public BasePage(WebDriver driver){
		
		AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 30);
		
		PageFactory.initElements(ajax, this);
		
		this.driver = driver;
		wait = new SeleniumUtilities(driver);
		js = (JavascriptExecutor) driver;
	}
	
	public void launchApplication(String url){
		System.out.println("Launching the URL :- "+url);
		driver.get(url);
	}
	
	public Object executeJSCommandWithArguments(String command, Object... args){
		
		Object x = (Object) js.executeScript("return "+command, args);
		return x;
	}
	
	public void executeSimpleJavascript(String command){
		js.executeScript(command);
	}
	
	public void closeBrowser(){
		driver.quit();
	}
	
	public boolean is_displayed(WebElement element){
		boolean flag= false;
		try{
			flag = element.isDisplayed();
		}catch(NoSuchElementException ex ){
			System.out.println("Element not found on the page");
		}
		return flag;
	}

	
	
}
