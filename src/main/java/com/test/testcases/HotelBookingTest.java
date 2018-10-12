package com.test.testcases;

import com.sun.javafx.PlatformUtil;
import com.test.pageobjects.HomePage;
import com.test.services.CommonUtils;
import com.test.services.ServiceInitializer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HotelBookingTest {

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
	public void verifyHotelSearch() {

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.shouldBeAbleToSearchForHotels();

		// verify that result appears for the provided hotel search
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='searchSummary']//span")));
		Assert.assertTrue(commonutils.isElementPresent(driver, By.xpath("//*[@class='searchSummary']//span")));

	}

	@AfterSuite
	public void closeBrowser() {
		// close the browser
		driver.quit();
	}

}
