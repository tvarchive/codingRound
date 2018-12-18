package com.clearTrip.pages;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;

import com.clearTrip.utils.DriverFactoy;

public class SignInTestPage extends DriverFactoy {

	public String signInErrorMsg() {
		// TODO Auto-generated method stub\
		return getDriver().findElement(By.id("errors1")).getText();

	}

	public static void clickOnSignInButton() {
		// TODO Auto-generated method stub
		getDriver().findElement(By.id("SignIn")).click();
		getDriver().switchTo().frame("modal_window");
		getDriver().findElement(By.id("signInButton")).click();

	}

	public static void clickOnTripsButton() {
		// TODO Auto-generated method stub
		getDriver().findElement(By.linkText("Your trips")).click();

	}

	
}
