package com.codinground.uicommon;



	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
    

	
/**
 * Common ui methods extends DriverFactory class
 * can be reused across pages
 * can be extended
 * 
 */


	public class UiCommonLibrary {
		
		private WebDriver driver;
		private Actions action;
		private Alert alert;
		private Select select;
		private JavascriptExecutor js;
		
		public UiCommonLibrary(WebDriver driver){
			 this.driver=driver;
			 action = new Actions(driver);
			 js = (JavascriptExecutor)driver;
		}				
			
 
		
		
		public void backspace(By xpath) {
			
			driver.findElement(xpath).sendKeys(Keys.BACK_SPACE);
			
		}
		
		public void actionOperationHandler(By xpath, String operation) {
			
			switch(operation) {
			
			case"mouse hover":
				action.moveToElement(driver.findElement(xpath)).perform();
			case"mouse hover and click":
				action.moveToElement(driver.findElement(xpath)).click().perform();
			
			}
			
		}

		public void sendKeysElem(By xpath, String value) {
			
			driver.findElement(xpath).clear();
			driver.findElement(xpath).sendKeys(value);
				
		}
		
		public void switchToFrameByIndex(int index) {
			driver.switchTo().frame(index);
		}
		
		public void switchToParentFrame() {
			driver.switchTo().parentFrame();
		}
		
		public void switchToFrameByNameOrId(String frameName) {
			
			driver.switchTo().frame(frameName);
			
		}
		
		public void jsClickElement(By xpath) {
			
			js.executeScript("arguments[0].click", driver.findElement(xpath));
			
		}
		
		public void sendKeysOneByOne(By xpath, String value) {
			
			
				try {
					driver.findElement(xpath).clear();
					for(int i = 0; i<value.length();i++) {
						String sb = new StringBuilder().append(value.charAt(i)).toString();
						driver.findElement(xpath).sendKeys(sb);
					    Thread.sleep(2000);
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}

		public <T> void waitFor(ExpectedCondition<T> expectedConditions,int timeOut) {

			new WebDriverWait(driver, timeOut).until(expectedConditions);

		}
		
		public String alertHandler(String operation) {
			
			alert = driver.switchTo().alert();
			String strToReturn = null; 
			
			switch(operation) {
			case"dissmis":
				alert.dismiss();
				 strToReturn = alert.getText()+": Alert Dissmissed";
			case"accept":
				alert.accept();
				strToReturn = alert.getText()+": Alert Accepted";
			case"getText":
				alert.getText();
				strToReturn = "Alert Text: "+alert.getText();
			}
		  return strToReturn;
		}
		
		public void clickElement(By xpath) {
			
			driver.findElement(xpath).click();
			
		}

		public boolean isElementDisplayed(By xpath) {
			boolean state= false; 
			  state = driver.findElement(xpath).isDisplayed(); 
         		return state;
		}

		public void implictWaitOn() {

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		}

		public void implicitWaitOff() {

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		}

		public void selectValue(By xpath, String value, String method) {

			 select = new Select(driver.findElement(xpath));

			switch (method) {

			case "byIndex":

				select.selectByIndex(Integer.valueOf(value));

			case "byValue":

				select.selectByValue(value);

			case "byVisibleText":

				select.selectByVisibleText(value);
			}
		}

		public boolean checkIfOptionEntered(By xpath, String expected) {
	    	boolean flag=false;
	    	waitFor(ExpectedConditions.presenceOfElementLocated(xpath), 10);
	    	if(driver.findElement(xpath).getAttribute("value").equalsIgnoreCase(expected)) {
	    		flag=true;
	    	}
	    	return flag;
	    }
		
		public boolean checkIfRadioButtonClicked(By xpath, String expected) {
	    	boolean flag=false;
	    	waitFor(ExpectedConditions.presenceOfElementLocated(xpath), 10);
	    	if(driver.findElement(xpath).getAttribute("checked").equalsIgnoreCase(expected)) {
	    		flag=true;
	    	}
	    	return flag;
	    }
	}

	
	
	
	
	

