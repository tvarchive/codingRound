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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {

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
	public void verifyHotelSearch() {

		commonutils.waitFor(2000);

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.shouldBeAbleToSearchForHotels();

		// verify that result appears for the provided hotel search
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='searchSummary']//span")));
		Assert.assertTrue(commonutils.isElementPresent(driver, By.xpath("//*[@class='searchSummary']//span")));
	}
	
	@AfterTest
	public void closeBrowser() {
		// close the browser
		driver.quit();
	}

}
