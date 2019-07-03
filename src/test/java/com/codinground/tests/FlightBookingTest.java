package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.SearchSummaryPage;
import com.codinground.uicommon.ModelTypeUi;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class FlightBookingTest {
    
	
   private static WebDriver driver;
   private static FlightBookingPage objFlightPage;
   private static DriverFactory objDriverFactory; 
   private static SearchSummaryPage objSearchSummaryPage;
    
@BeforeTest
  public void before() {
	objDriverFactory = new DriverFactory();
	objDriverFactory.launchUrl();
	driver = objDriverFactory.getDriver();
  }

    @Test
    public void testThatResultsAppearForAOneWayJourney() { 
       
    	objFlightPage = new FlightBookingPage(driver).get();
    	
        objFlightPage = objFlightPage.clickOneWay();
        Assert.assertTrue(objFlightPage.chechIfOnWayClicked(ModelTypeUi.ATTRIBUTE_CHECKED));
        objFlightPage = objFlightPage.enterDepartFrom("Bangalore");
        Assert.assertTrue(objFlightPage.checkIfFromEntered(ModelTypeUi.BANGALORE));
        objFlightPage = objFlightPage.enterToArrive("New Delhi");
        Assert.assertTrue(objFlightPage.checkIfToEntered(ModelTypeUi.NEW_DELHI));
        objFlightPage = new FlightBookingPage(driver).get();
        objFlightPage.pickFromDate("3/july/2019");
        objSearchSummaryPage = objFlightPage.clickSearchBtn();
        Assert.assertTrue(objSearchSummaryPage.verifyIfRelevant("Bangalore", "New Delhi"));
    }

 @AfterTest
 public void after() {
	
	driver.quit();
	
 }
  
}
