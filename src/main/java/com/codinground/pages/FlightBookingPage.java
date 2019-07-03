package com.codinground.pages;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.codinground.uicommon.UiCommonLibrary;


    public class FlightBookingPage extends LoadableComponent<FlightBookingPage>{

    	
    	private WebDriver driver;
    	
		private static UiCommonLibrary objCommon;
		//private static SimpleDateFormat formatter = new SimpleDateFormat("dd/mmmm/yyyy");
	    private static String delimiter = null;
	    private static String day = null;
	    private static String month = null;
	    private static String year = null;
	    private static String strActualMonth = null;
	    private static String strActualYear = null;
	    private static String[] dateArr;
	    
		private static final String STR_DATE_PICKER = "//*[@id='ui-datepicker-div']";
		private static final String STR_MONTH_FIRST_LAST = STR_DATE_PICKER+"/child::div";
		
		private static final By LOCATOR_BUTTON_ONEWAY = By.id("OneWay");
		private static final By LOCATOR_INPUT_FROM = By.id("FromTag");
		private static final By LOCATOR_INPUT_FROM_CHECK = By.id("From");
		private static final By LOCATOR_INPUT_TO = By.id("ToTag");
		private static final By LOCATOR_INPUT_TO_CKECK = By.id("To");
		private static final By LOCATOR_BUTTON_SEARCH = By.id("SearchBtn");
		private static final By LOCATOR_MONTH_FIRST_LAST = By.xpath(STR_MONTH_FIRST_LAST);
		private static final By LOCATOR_LINK_FLIGHTS = By.linkText("Flights");
		private static final By LOCATOR_LINK_HOTELS = By.linkText("Hotels");
		private static final By LOCATOR_LIST_ORIGIN_OPT = By.xpath("//*[@id='ui-id-1']/li");
		private static final By LOCATOR_LIST_DESTINATION_OPT = By.xpath("//*[@id='ui-id-2']/li");
		private static final By LOCATOR_BUTTON_CALANDER_ICON = By.linkText("Cal");
		private static final By LOCATOR_LINK_YOUR_TRIPS = By.linkText("Your trips");
		private static final By LOCATOR_BUTTON_SIGNIN = By.id("SignIn");
		
        private static WebElement btnOneWay;
        private static WebElement txtFrom;
        private static WebElement txtTo;
        private static WebElement btnSearch;
        private static WebElement lnkFlights;
        private static WebElement lnkHotels;
        private static WebElement lnkYourTrips;
        private static List<WebElement> elemDayRows;
        private static List<WebElement> elemColumns;
        private static WebElement actualDay;
        private static WebElement txtFromCheck;
        private static WebElement txtToCheck;      
        private static List<WebElement> originOptions;
        private static List<WebElement> destinationOptions;
        private static List <WebElement> monthFirstLast;
      
        public FlightBookingPage(WebDriver driver) {
			this.driver = driver;
			objCommon = new UiCommonLibrary(driver);
		}
    
        
        public FlightBookingPage enterDepartFrom(String place) {
    	for(int i =0; i<+3;i++) {
    	try {	
    		objCommon.sendKeysOneByOne(LOCATOR_INPUT_FROM,place);
    		objCommon.waitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_LIST_ORIGIN_OPT),10);
    		driver.findElements(LOCATOR_LIST_ORIGIN_OPT).get(0).click();
    		break;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
    	return this;
    }
    
    public boolean checkIfFromEntered(String excpected) {
    	
    	return objCommon.checkIfOptionEntered(LOCATOR_INPUT_FROM_CHECK,excpected);
    	
    }
    
    public boolean checkIfToEntered(String excpected) {
    	
    	return objCommon.checkIfOptionEntered(LOCATOR_INPUT_TO_CKECK,excpected);
    	
    }
    
    public FlightBookingPage enterToArrive(String place) {
    	for(int i =0; i<+3;i++) {
    		try {
        		objCommon.sendKeysOneByOne(LOCATOR_INPUT_TO,place);
          		objCommon.waitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_LIST_DESTINATION_OPT),10);
        		driver.findElements(LOCATOR_LIST_DESTINATION_OPT).get(0).click();
        		break;
        	}catch(Exception e) {
        		e.printStackTrace();
        	}	
    	}
    	
    	return this;
    }
    
    public FlightBookingPage clickOneWay() {
    	try {
    		if(objCommon.isElementDisplayed(LOCATOR_BUTTON_ONEWAY)) {
    			if(!driver.findElement(LOCATOR_BUTTON_ONEWAY).getAttribute("checked").equals("true")) {
    		     objCommon.clickElement(LOCATOR_BUTTON_ONEWAY);
    		    }
    	    }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
       
    	return this;
    }
    
   public boolean chechIfOnWayClicked(String expected) {
	   
	   return objCommon.checkIfRadioButtonClicked(LOCATOR_BUTTON_ONEWAY,expected);
	   
   }
   
   public void clickYourTrips() {
	   
	   objCommon.clickElement(LOCATOR_LINK_YOUR_TRIPS);
	  
   }
   
   public SignInPage clickSignInBtn(String name) {
	
	   objCommon.clickElement(LOCATOR_BUTTON_SIGNIN);
	   //objCommon.switchToFrameByIndex(frameIndex);
	   objCommon.switchToFrameByNameOrId(name);
	   
	   return new SignInPage(driver).get();
	   
   }
    
    public SearchSummaryPage clickSearchBtn() {
    
    	if(objCommon.isElementDisplayed(LOCATOR_BUTTON_SEARCH)) {
    		objCommon.clickElement(LOCATOR_BUTTON_SEARCH);
    	}
    	return new SearchSummaryPage(driver).get();
    }
    
public HotelBookingPage clickHotelLnk() {
		
		objCommon.clickElement(LOCATOR_LINK_HOTELS);;
		return new HotelBookingPage(driver).get();
	}
    
        
     public void pickFromDate(String date) {     	
    	 
			delimiter= "/";
        	dateArr = date.split(delimiter);
        	day = dateArr[0];
        	month = dateArr[1];
        	year = dateArr[2];
        	monthFirstLast = driver.findElements(LOCATOR_MONTH_FIRST_LAST);
        	int i=0,j=0,k=0;
    		for (i=0; i<monthFirstLast.size();i++){
    			strActualMonth = driver.findElement(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/div/div/span[1]")).getText();
    			strActualYear = driver.findElement(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+2)+"]/div/div/span[2]")).getText();
    			if(strActualMonth.equalsIgnoreCase(month)&&strActualYear.equalsIgnoreCase(year)){				   								
    				elemDayRows = driver.findElements(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr"));
    				for (j=0;j<elemDayRows.size();j++){
    					elemColumns = driver.findElements(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td"));
    					for(k=0;k<elemColumns.size();k++) {
    						if(!driver.findElement(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td["+(k+1)+"]")).getAttribute("class").contains("disabled")) {
    						actualDay=driver.findElement(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td["+(k+1)+"]/a"));
    						   if(actualDay.getText().equals(day)){
        						actualDay.click();
    							   if(actualDay.isDisplayed())
    								objCommon.moveToElementClick(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td["+(k+1)+"]/a"));
    							   i=monthFirstLast.size();
    							   j=elemDayRows.size();
    							   k=elemColumns.size();
        					   }
    						
    						}
    						
    					}
       				}								
    				break;	
    			}			
    			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();
    	 		pickFromDate(date);
    	 		break;
    		}
    		} 
    		
			
     	 	
        	
		@Override
		protected void load() {
			
			 btnOneWay =  driver.findElement(LOCATOR_BUTTON_ONEWAY);
	         txtFrom = driver.findElement(LOCATOR_INPUT_FROM);
	         txtTo =driver.findElement(LOCATOR_INPUT_TO);
	         btnSearch =  driver.findElement(LOCATOR_BUTTON_SEARCH);
	         lnkFlights = driver.findElement(LOCATOR_LINK_FLIGHTS);
	         lnkHotels = driver.findElement(LOCATOR_LINK_HOTELS);
	         lnkYourTrips = driver.findElement(LOCATOR_LINK_YOUR_TRIPS);
		}

		@Override
		protected void isLoaded() throws Error {
		        Assert.assertTrue(driver.findElement(LOCATOR_BUTTON_ONEWAY).isDisplayed());
		        Assert.assertTrue(driver.findElement(LOCATOR_INPUT_FROM).isDisplayed());
		        Assert.assertTrue(driver.findElement(LOCATOR_INPUT_TO).isDisplayed());
		        Assert.assertTrue(driver.findElement(LOCATOR_BUTTON_SEARCH).isDisplayed());
		        Assert.assertTrue(driver.findElement(LOCATOR_LINK_FLIGHTS).isDisplayed());
		        Assert.assertTrue(driver.findElement(LOCATOR_LINK_HOTELS).isDisplayed());
		        Assert.assertTrue(driver.findElement(LOCATOR_LINK_YOUR_TRIPS).isDisplayed()); 
			
		}

		
	}


