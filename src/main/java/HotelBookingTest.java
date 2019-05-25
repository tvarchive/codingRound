import com.sun.javafx.PlatformUtil;

import Pages.HotelBooking;
import Testbase.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelBookingTest extends TestBase{

	   
	HotelBooking  HB ;
	
	@BeforeMethod
	public void setupHomePage()
	{
		BrowserUrllaunch();		
	}

    @Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException
    {

      HB = new HotelBooking();
      HB.HotelLinkClick();
      HB.localityTextBox(prop.getProperty("HotelLocationName"));
      waitFor(9000);
      HB.travellerSelection(prop.getProperty("TravelSelection"));
    }

     @AfterMethod
     public void  tearDown()
     {
    	driver.quit(); 
     }
     
}
© 2019 GitHub, Inc.
