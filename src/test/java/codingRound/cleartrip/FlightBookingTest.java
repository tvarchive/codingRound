package codingRound.cleartrip;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import codingRound.commonLib.CommonFunctionsLib;
import codingRound.utility.Testbase;

public class FlightBookingTest extends Testbase{


    	@Test(description = "to create a new one way booking")//, timeOut = 180000)
    	
    	public void oneWayflightBooking() throws Exception {
    		
    		CommonFunctionsLib common = new CommonFunctionsLib();
    		String filePath = testConfig.getRunTimeProperty("Resources") + testConfig.getRunTimeProperty("fileSeparator")
			+ testConfig.getRunTimeProperty("dataproviderpath");
    		Object[][] bookingDetails = common.readExcel(filePath, "FlightBooking");
    		driver.get(testConfig.getRunTimeProperty("environment"));
    		FlightBooking fb = new FlightBooking(driver, logger);
    		fb.flightBooking(bookingDetails,testConfig, common);
    		logger.log(LogStatus.INFO, "one way booking", "One way booking");
    		
    		fb.isResultAppeared( testConfig,common);
    		logger.log(LogStatus.INFO, "Verify search results", "search result appeared ");
    		// cs.checkImageStdStory();

    	}
}
