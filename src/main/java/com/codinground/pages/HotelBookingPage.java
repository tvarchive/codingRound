package com.codinground.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.codinground.uicommon.UiCommonLibrary;

public class HotelBookingPage extends LoadableComponent<HotelBookingPage>{

	
	   private WebDriver driver;
	   private static UiCommonLibrary objCommon;
	   private static String delimiter = null;
	    private static String day = null;
	    private static String month = null;
	    private static String year = null;
	    private static String strActualMonth = null;
	    private static String strActualYear = null;
	    private static String[] dateArr;
	   
	   private static final By LOCATOR_OPTIONS = By.xpath("//*[@id='ui-id-1']/li");
	   private static final String STR_DATE_PICKER = "//*[@id='ui-datepicker-div']";
	   private static final String STR_MONTH_FIRST_LAST = STR_DATE_PICKER+"/child::div";
	   private static final By LOCATOR_MONTH_FIRST_LAST = By.xpath(STR_MONTH_FIRST_LAST);
	   private static final By LOCATOR_BUTTON_SEARCH_HOTEL = By.id("SearchHotelsButton");
	   
	 
	    
	    @FindBy(linkText = "Hotels")
	    private static WebElement hotelLink;

	    @FindBy(id = "Tags")
	    private static WebElement localityTextBox;

	    @FindBy(id = "SearchHotelsButton")
	    private static WebElement searchButton;

	    @FindBy(id = "travellersOnhome")
	    private static WebElement travellerSelection;
   
	    private static List <WebElement> elemOptions;
	    private static List <WebElement> monthFirstLast;
	    private static List <WebElement> elemDayRows;
	    private static List <WebElement> elemColumns;
	    private static WebElement actualDay;
	
	 public HotelBookingPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		objCommon = new UiCommonLibrary(driver);
		
	}
	
	public HotelBookingPage clickHotelLnk() {
		
		hotelLink.click();
		return this;
	}
	
	public HotelBookingPage enterLocationTxt(String place) {
		
		objCommon.sendKeysOneByOne(By.id("Tags"), place);
		objCommon.waitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_OPTIONS), 10);
		driver.findElements(LOCATOR_OPTIONS).get(1).click();
		return this;
	}
	
	public HotelBookingPage selectTravellers(String text) {
		objCommon.waitFor(ExpectedConditions.visibilityOf(driver.findElement(By.id("travellersOnhome"))), 10);
		objCommon.selectValue(By.id("travellersOnhome"), text,"byVisibleText" );
		return this;
	}
	
	public SearchSummaryPage clickSearchBtn() {
	    
    	if(objCommon.isElementDisplayed(LOCATOR_BUTTON_SEARCH_HOTEL)) {
    		objCommon.clickElement(LOCATOR_BUTTON_SEARCH_HOTEL);
    	}
    	return new SearchSummaryPage(driver).get();
    }
	
	public void pickDate(String date) {     	
   	 
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
							   //if(actualDay.isDisplayed())
								//objCommon.actionOperationHandler(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td["+(k+1)+"]/a"), "mouse hover and click");
							   //objCommon.jsClickElement(By.xpath(STR_MONTH_FIRST_LAST+"["+(i+1)+"]/table/tbody/tr["+(j+1)+"]/td["+(k+1)+"]/a"));
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
	 		pickDate(date);
	 		break;
		}
		} 
	
	@Override
	protected void load() {
		
		hotelLink = driver.findElement(By.linkText("Hotels"));
		localityTextBox = driver.findElement(By.id("Tags"));
		searchButton = driver.findElement(By.id("SearchHotelsButton"));
		travellerSelection = driver.findElement(By.id("travellersOnhome"));
	}

	@Override
	protected void isLoaded() throws Error {
		
		Assert.assertTrue(driver.findElement(By.linkText("Hotels")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("Tags")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("SearchHotelsButton")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("travellersOnhome")).isDisplayed());
		
		
	}

}
