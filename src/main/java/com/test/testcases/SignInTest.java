package com.test.testcases;

import com.sun.javafx.PlatformUtil;
import com.test.services.CommonUtils;
import com.test.services.ServiceInitializer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SignInTest {

	static WebDriver driver = null;
	WebDriverWait wait;
	CommonUtils commonutils = new CommonUtils();

	@BeforeSuite
	public void setup() {
		System.out.println("Starting Setup");
		ServiceInitializer service = new ServiceInitializer();
		driver = service.getDriver();
		wait = new WebDriverWait(driver, 10);
		System.out.println("Setup Completed");
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		commonutils.waitFor(2000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();

		driver.switchTo().frame("modal_window"); // switched to the frame on the page

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInButton")));
		driver.findElement(By.id("signInButton")).click();

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
	}

	@AfterSuite
	public void closeBrowser() {
		// close the browser
		driver.quit();
	}

}
