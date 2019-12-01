package codingRound.cleartrip;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.javafx.PlatformUtil;

import codingRound.commonLib.CommonFunctionsLib;
import codingRound.utility.Testbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends Testbase{

@Test(description = "To verify sign in error if sign in details are missing")//, timeOut = 180000)
    	
    	public void verifySignIn() throws Exception {
    		
    		CommonFunctionsLib common = new CommonFunctionsLib();
    		driver.get(testConfig.getRunTimeProperty("environment"));
    		SignIn signin = new SignIn(driver, logger);
    		signin.signIn(testConfig, common);
    		logger.log(LogStatus.INFO, "Verify sign in with missing details", "Error message is thrown");
    		
    		//hb.isResultAppeared( testConfig,common);
    		//logger.log(LogStatus.INFO, "Verify search results", "search result appeared ");
    		// cs.checkImageStdStory();

    	}

}
