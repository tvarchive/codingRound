package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.SignInPage;
import supportlibraries.TestBase;

public class SignInTest extends TestBase {

    @Test
    public void shouldThrowErrorIfSignInDetailsAreMissing() {
    	
        System.out.println(" Started Validated Sign In error message");
        String expectedErrorMessage = "There were errors in your submission";
        
        SignInPage signInPage = new SignInPage(scriptHelper);
        
        signInPage.clickYourTripLink();
        signInPage.selectSignInButton();
        // Switching to iframe
        signInPage.switchToFrame();
        signInPage.selectPopupSignInButton();
        Assert.assertTrue(signInPage.verifySignInErrorMessage(expectedErrorMessage));
        
    }


}
