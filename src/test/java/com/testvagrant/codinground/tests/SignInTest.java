package com.testvagrant.codinground.tests;
import org.junit.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testvagrant.codinground.initializations.Initiator;

public class SignInTest {
	Initiator test;
	
	@BeforeClass
	public void startTest(){
		test = new Initiator("chrome");
	}
	
	@Test
	public void test1(){
		test.flightSearchPage.launchApplication("https://www.cleartrip.com/");
	}
	
	@AfterClass
	public void closeSession(){
		test.flightSearchPage.closeBrowser();
	}	

}
