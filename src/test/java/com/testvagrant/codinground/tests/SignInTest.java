package com.testvagrant.codinground.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;

public class SignInTest {
	Initiator test;

	@BeforeClass
	public void startTest(){
		test = new Initiator("chrome");
		test.homepage.launchApplication("https://www.cleartrip.com");
	}
	
	@Test
	public void blank_sign_in_test(){
		test.signInModal = test.homepage.openSignInModal();
		Assert.assertEquals(test.signInModal.blankFormSignIn(), "There were errors in your submission");
		
		Reporter.log("Sign In test passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.homepage.closeBrowser();
	}
}
