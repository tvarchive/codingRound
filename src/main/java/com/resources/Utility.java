package com.resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.sun.javafx.PlatformUtil;

public class Utility {

	public WebDriver driver;
	
	@BeforeTest
	public void before(){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		setDriverPath();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);	
		driver.get("https://www.cleartrip.com/");
	}

	
	@SuppressWarnings("restriction")
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
	
	
	@AfterTest
	public void afterTest(){
		//close the browser
		driver.quit();
	}
}
