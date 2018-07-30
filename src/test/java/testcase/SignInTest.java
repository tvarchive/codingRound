package test.java.testcase;

import test.java.framework.BaseTest;
import test.java.pages.HomePage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * SignInTest.java
 * 
 * Contains tests for sign in page
 *
 */

@Listeners(test.java.framework.TestListener.class)

public class SignInTest extends BaseTest {
	
	HomePage homePage;
	
	/**
	 * Checks for error message on missing credentials
	 */

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	Reporter.log("[INFO] Starting testcase 'shouldThrowAnErrorIfSignInDetailsAreMissing'", true);
    	
    	homePage = new HomePage(driver);
    	
    	homePage.openSignInModal();
    	
    	homePage.signInWithoutCredentials();
    	
    	String errors = homePage.getSignInErrors();
        
        Assert.assertTrue(errors.contains("There were errors in your submission"));
    	Reporter.log("[INFO] Starting testcase 'shouldThrowAnErrorIfSignInDetailsAreMissing'", true);
    }



}