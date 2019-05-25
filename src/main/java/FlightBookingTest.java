import com.sun.javafx.PlatformUtil;

import Pages.FlightBooking;
import Testbase.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends TestBase {

	FlightBooking FB;
	
	@BeforeMethod
	public void setupHomePage()
	{
		BrowserUrllaunch();
	}
	
    @Test
    public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {

        FB=  new FlightBooking();
      //wait for the auto complete options to appear for the origin
        FB.fromLocationSelected(prop.getProperty("FromLocationName"));
        waitFor(3000);
        
        //wait for the auto complete options to appear for the destination       
        FB.toLocationSelected(prop.getProperty("ToLocationName"));
        waitFor(7000);
        FB.spiltdateandSelectdate(prop.getProperty("date"));

        waitFor(3000);    
        
      //all fields filled in. Now click on search
        FB.searchBtnClick();
        
      //verify that result appears for the provided journey search
        Assert.assertTrue(FB.isElementPresent());
     } 
    @AfterMethod
    public void  tearDown()
    {
   	driver.quit(); 
    }
   
}
