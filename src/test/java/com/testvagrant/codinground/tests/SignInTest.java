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
		test = new Initiator(test.config.getProperty("browser"));
		test.homepage.launchApplication(test.config.getProperty("url"));
	}
	
	@Test
	public void blank_sign_in_test(){
		test.signInModal = test.homepage.openSignInModal();
		Assert.assertEquals(test.signInModal.blankFormSignIn(), test.config.getProperty("error_blank_sign_in"));
		
		Reporter.log("Sign In test passed",true);
	
	}
	
	@AfterClass
	public void closeSession(){
		test.homepage.closeBrowser();
	}
}
