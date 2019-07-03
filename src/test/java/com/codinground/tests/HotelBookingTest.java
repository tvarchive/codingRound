package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.HotelBookingPage;
import com.codinground.pages.SearchSummaryPage;
import com.codinground.uicommon.UiCommonLibrary;
import com.sun.javafx.PlatformUtil;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

	  private WebDriver driver;
   

    /*@FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;*/
    
    private static HotelBookingPage objHotelPage;
    private static DriverFactory objDriverFactory;
    private static UiCommonLibrary objCommonLib; 
    private static SearchSummaryPage objSearchSummaryPage;
    private static FlightBookingPage objFlightPage;
 @BeforeTest
   public void before() {
 	objDriverFactory = new DriverFactory();
 	objDriverFactory.launchUrl();
 	driver = objDriverFactory.getDriver();
 	objCommonLib = new UiCommonLibrary(driver);
   }
    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {

    	objFlightPage = new FlightBookingPage(driver).get();
    	objHotelPage = objFlightPage.clickHotelLnk();
    	objHotelPage = objHotelPage.enterLocationTxt("Indiranagar, Bangalore");
    	objHotelPage.pickDate("3/july/2019");
    	objHotelPage.pickDate("4/july/2019");
    	objHotelPage = objHotelPage.selectTravellers("1 room, 2 adults");
    	objSearchSummaryPage = objHotelPage.clickSearchBtn();
    	Assert.assertTrue(objSearchSummaryPage.verifyIfRelevant("Bangalore"));
    
    }

   
    @AfterTest
    public void after() {
    	
    	driver.quit();
    	
    }
	

}
