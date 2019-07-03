package com.codinground.tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.SignInPage;
import com.codinground.reportutils.ReportListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SignInTest extends ReportListener{

    private WebDriver driver;
    private static DriverFactory objDriverFactory;
    private static FlightBookingPage objFlightPage;
    private static SignInPage objSignInPage;
    private static ReportListener objReportListener;
    private static ExtentReports reports;
    private static ExtentTest test;
    
    @BeforeTest
    public void beforeTest() {
    	objDriverFactory = new DriverFactory();
    	objDriverFactory.launchUrl();
    	driver = objDriverFactory.getDriver();
    	objSignInPage = new SignInPage(driver);
    	driver.manage().window().maximize();
    }
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        
        objFlightPage = new FlightBookingPage(driver).get();
        objFlightPage.clickYourTrips();
        objSignInPage = objFlightPage.clickSignInBtn("modal_window");
        objSignInPage.clickSignIn();
        Assert.assertTrue(objSignInPage.verifyErrorMsg());
       
      
        
    }
    
    @AfterTest
    public void afterTest() {
    	objDriverFactory.destroyDriver();
    	
    }

}
