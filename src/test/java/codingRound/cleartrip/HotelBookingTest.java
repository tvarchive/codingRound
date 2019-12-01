package codingRound.cleartrip;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.javafx.PlatformUtil;

import codingRound.commonLib.CommonFunctionsLib;
import codingRound.utility.Testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest extends Testbase{

@Test(description = "to create a new hotel booking")//, timeOut = 180000)
	
	public void hotelBooking() throws Exception {
		
		CommonFunctionsLib common = new CommonFunctionsLib();
		driver.get(testConfig.getRunTimeProperty("environment"));
		HotelBooking hb = new HotelBooking(driver, logger);
		hb.hotelBooking(testConfig, common);
		logger.log(LogStatus.INFO, "Hotel booking", "Hotel booking successful");
		
		//hb.isResultAppeared( testConfig,common);
		//logger.log(LogStatus.INFO, "Verify search results", "search result appeared ");
		// cs.checkImageStdStory();

	}


}
