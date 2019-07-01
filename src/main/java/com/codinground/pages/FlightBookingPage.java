package com.codinground.pages;


import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.codinground.uicommon.UiCommonLibrary;


    public class FlightBookingPage extends LoadableComponent<FlightBookingPage>{

    	
    	
    	public FlightBookingPage(WebDriver driver) {
			this.driver = driver;
			objCommon = new UiCommonLibrary();
		}
        
    	public WebDriver driver;
		private static UiCommonLibrary objCommon;
		private static SimpleDateFormat formatter = new SimpleDateFormat("dd/mmmm/yyyy");
	    private static String delimiter = null;
	    private static String day = null;
	    private static String month = null;
	    private static String year = null;
	    private static String strActualMonth = null;
	    private static String strActualYear = null;
	    private static String[] dateArr;
	    
	    
		private static final String DATE_PICKER = "//*[@id='ui-datepicker-div']";
		private static final String MONTH_FIRST_LAST = DATE_PICKER+"/child::div";
		
		
        private WebElement btnOneWay =  driver.findElement(By.id("OneWay"));
        private WebElement txtFrom = driver.findElement(By.id("FromTag"));
        private WebElement txtFromCheck = driver.findElement(By.id("From"));
        private List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        private WebElement txtTo =driver.findElement(By.id("ToTag"));
        private WebElement txtToCheck = driver.findElement(By.id("To"));
        private List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        private WebElement btnSearch =  driver.findElement(By.id("SearchBtn"));
        //private driver.findElement(By.xpath(("//*[@id='ui-datepicker-div']/child::div[1]/table/tbody/tr[5]/td[7]/a"))).click();
        private List <WebElement> monthFirstLast = driver.findElements(By.xpath(MONTH_FIRST_LAST));
        private WebElement lnkFlights = driver.findElement(By.linkText("Flights"));
        private WebElement lnkHotels = driver.findElement(By.linkText("Hotels"));
        private List<WebElement> elemDayRows;
        private List<WebElement> elemColumns;
        private WebElement actualDay;
        
        
        
        
    public FlightBookingPage enterDepartFrom(String place) {
    	
    	try {
    		
    		txtFrom.clear();
    		txtFrom.sendKeys(place);
    		objCommon.waitFor(ExpectedConditions.visibilityOf(originOptions.get(0)),10);
    		originOptions.get(0).click();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return this;
    }
    
    public boolean checkIfFromEntered(String excpected) {
    	return objCommon.checkIfOptionEntered(txtFromCheck,excpected);
    	
    }
    
    public boolean checkIfToEntered(String excpected) {
    	return objCommon.checkIfOptionEntered(txtToCheck,excpected);
    	
    }
    
    public FlightBookingPage enterToArrive(String place) {
        
    	try {
    		
    		txtTo.clear();
    		txtTo.sendKeys(place);
    		objCommon.waitFor(ExpectedConditions.visibilityOf(destinationOptions.get(0)),10);
    		originOptions.get(0).click();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return this;
    }
    
    public FlightBookingPage clickOneWay() {
    	
    	if(objCommon.isElementDisplayed(btnOneWay)) {
    		
    	try {
    		if(!btnOneWay.getAttribute("checked").equalsIgnoreCase("checked")) {
    		btnOneWay.click();
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
       }
    	return this;
    }
    
   public boolean chechIfOnWayClicked(String expected) {
	   
	   return objCommon.checkIfRadioButtonChecked(btnOneWay,expected);
	   
   }
    
    public SearchSummaryPage clickSearchBtn() {
    	  
    	if(objCommon.isElementDisplayed(btnSearch)) {
    		btnSearch.click();
    	}
    	return new SearchSummaryPage(driver);
    }
    
        
     public void pickFromDate(String date) {     	
        	date = formatter.format(date);
        	delimiter= "/";
        	dateArr = date.split(delimiter);
        	day = dateArr[0];
        	month = dateArr[1];
        	year = dateArr[2];
        	
    		for (int i=0; i<monthFirstLast.size();i++){
    			strActualMonth = driver.findElement(By.xpath(MONTH_FIRST_LAST+"["+(i+1)+"]/div/div/span[1]")).getText();
    			strActualYear = driver.findElement(By.xpath(MONTH_FIRST_LAST+"["+(i+2)+"]/div/div/span[2]")).getText();
    			if(strActualMonth.equalsIgnoreCase(month)&&strActualYear.equalsIgnoreCase(year)){				   								
    				elemDayRows = driver.findElements(By.xpath(MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr"));
    				for (int j=0;i<elemDayRows.size();i++){
    					elemColumns = driver.findElements(By.xpath(MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td"));
    					for(int k=0;k<elemColumns.size();k++) {
    						actualDay = driver.findElement(By.xpath(MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td["+(k+1)+"]/a"));
    						if(actualDay.getText().equals(day)){
        						actualDay.click();		
        						//return;
        					}	
    					}
    				}								
    				
    			}			
    					
    		}
    		driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
    		pickFromDate(date);	
    		
    	}

		@Override
		protected void load() {
			// TODO Auto-generated method stub
			objCommon.waitFor(ExpectedConditions.visibilityOf(btnOneWay), 10);
			objCommon.waitFor(ExpectedConditions.visibilityOf(txtFrom), 10);
			objCommon.waitFor(ExpectedConditions.visibilityOf(txtTo), 10);
			objCommon.waitFor(ExpectedConditions.visibilityOf(btnSearch), 10);
			objCommon.waitFor(ExpectedConditions.visibilityOf(lnkFlights), 10);
			objCommon.waitFor(ExpectedConditions.visibilityOf(lnkHotels), 10);
			
		}

		@Override
		protected void isLoaded() throws Error {
		        Assert.assertTrue(btnOneWay.isDisplayed());
		        Assert.assertTrue(txtFrom.isDisplayed());
		        Assert.assertTrue(txtTo.isDisplayed());
		        Assert.assertTrue(btnSearch.isDisplayed());
		        Assert.assertTrue(lnkFlights.isDisplayed());
		        Assert.assertTrue(lnkHotels.isDisplayed());
		        
			
		}

		
	}


