package com.codinground.uicommon;



	import java.util.List;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
    import com.codinground.driverutiils.DriverFactory;

	
/**
 * Common ui methods extends DriverFactory class
 * can be reused across pages
 * can be extended
 * 
 */


	public class UiCommonLibrary extends DriverFactory {
		
		
		private Actions action;
		
		public UiCommonLibrary(){
			
			 action = new Actions(driver);
		
		}

		public void waitFor(ExpectedCondition<WebElement> expectedConditions,int timeOut) {

			new WebDriverWait(driver, timeOut).until(expectedConditions);

		}

		public boolean isElementDisplayed(WebElement element) {
			boolean state= false; 
			try {
        	  state = element.isDisplayed(); 
          }catch(NoSuchElementException e) {
        	e.printStackTrace();  
          }
			
			return state;
		}

		public void implictWaitOn(WebDriver driver) {

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		public void implicitWaitOff(WebDriver driver) {

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		}

		public void selectValue(WebElement element, String value, String method) {

			Select select = new Select(element);

			switch (method) {

			case "byIndex":

				select.selectByIndex(Integer.valueOf(value));

			case "byValue":

				select.selectByValue(value);

			case "byVisibleText":

				select.selectByVisibleText(value);
			}
		}

		public boolean checkIfOptionEntered(WebElement elem, String expected) {
	    	boolean flag=false;
	    	waitFor(ExpectedConditions.visibilityOf(elem), 10);
	    	if(elem.getAttribute("value").equalsIgnoreCase(expected)) {
	    		flag=true;
	    	}
	    	return flag;
	    }
		
		public boolean checkIfRadioButtonChecked(WebElement elem, String expected) {
	    	boolean flag=false;
	    	waitFor(ExpectedConditions.visibilityOf(elem), 10);
	    	if(elem.getAttribute("checked").equalsIgnoreCase(expected)) {
	    		flag=true;
	    	}
	    	return flag;
	    }
	}

	
	
	
	
	

