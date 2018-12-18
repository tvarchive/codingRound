package com.clearTrip.Tests;
import com.clearTrip.pages.SignInTestPage;
import com.clearTrip.utils.DriverFactoy;
import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends SignInTestPage {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		SignInTestPage.clickOnTripsButton();
		SignInTestPage.clickOnSignInButton();

		String errors1 = signInErrorMsg();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
	}

}
