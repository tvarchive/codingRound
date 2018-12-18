package com.clearTrip.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.clearTrip.pages.SignInTestPage;
import com.sun.javafx.PlatformUtil;

public class DriverFactoy {

	public DriverFactoy() {
		// TODO Auto-generated constructor stub
		setDriverPath();
		driver = new ChromeDriver();
	}

	protected  static WebDriver driver;
	
	public static  WebDriver getDriver() {
		return driver;
	};
	
	
	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

	public static void waitFor(WebElement element) {
		try {
			WebDriverWait wait= new WebDriverWait(getDriver(),5000);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@BeforeTest
	public void launchApplication() {
		driver.get("https://www.cleartrip.com/");
	}

	@AfterTest
	public void quitDriver() {
		// close the browser
		driver.quit();
	}
}
