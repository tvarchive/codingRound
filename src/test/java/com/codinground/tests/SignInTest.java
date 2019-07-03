package com.codinground.tests;
import com.codinground.driverutiils.DriverFactory;
import com.codinground.pages.FlightBookingPage;
import com.codinground.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

    private WebDriver driver;
    private static DriverFactory objDriverFactory;
    private static FlightBookingPage objFlightPage;
    private static SignInPage objSignInPage;

    @BeforeTest
    public void beforeTest() {
    	objDriverFactory = new DriverFactory();
    	objDriverFactory.launchUrl();
    	driver = objDriverFactory.getDriver();
    	objSignInPage = new SignInPage(driver);
    	
    }
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        
        objFlightPage = new FlightBookingPage(driver).get();
        objFlightPage.clickYourTrips();
        objSignInPage = objFlightPage.clickSignInBtn(1);
        objSignInPage.clickSignIn();
        Assert.assertTrue(objSignInPage.verifyErrorMsg());
        
    }
    
    @AfterTest
    public void afterTest() {
    	
    	this.driver.quit();
    }

}
