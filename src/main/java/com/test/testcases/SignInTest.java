package com.test.testcases;

import com.sun.javafx.PlatformUtil;
import com.test.services.CommonUtils;
import com.test.services.ServiceInitializer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

	WebDriver driver = null;
	CommonUtils commonutils = new CommonUtils();
	WebDriverWait wait;

	@BeforeTest
	public void setup() {
		ServiceInitializer service = new ServiceInitializer();
		driver = service.getDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		commonutils.waitFor(2000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();

		driver.switchTo().frame("modal_window"); // switched to the frame on the page

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInButton")));
		driver.findElement(By.id("signInButton")).click();

		// verifying the error message
		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
	}

	@AfterTest
	public void closeBrowser() {
		// close the browser
		driver.quit();
	}

}
