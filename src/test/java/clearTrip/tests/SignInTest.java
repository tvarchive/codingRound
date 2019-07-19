package clearTrip.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import clearTrip.coreFramework.BaseTest;
import clearTrip.pages.SignInPage;

public class SignInTest extends BaseTest {
	
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		//the driver config and launch has been taken care of in BaseTest class 
       
    	SignInPage signIn = new SignInPage(driver);
    	signIn.clickYourTripsLink();
    	signIn.clickSignInLink();
    	signIn.switchToFrameModalWindow();
    	signIn.clickSignInButtonInOverlayWindow();
    	
        Assert.assertTrue((signIn.getErrors()).contains("There were errors in your submission"));
    }
	//unused methods removed 

}