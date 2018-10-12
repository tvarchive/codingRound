package com.test.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sun.javafx.PlatformUtil;

public class ServiceInitializer {

	public static WebDriver driver;
	static Properties properties;
	static ServiceInitializer service;
	WebDriverWait wait;

	public ServiceInitializer() {
		loadProperties();
		setDriverPath();
	}

	private void loadProperties() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDriverPath() {

		if (PlatformUtil.isMac()) {
			System.out.println("This is Mac");
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.out.println("This is Windows");
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}

	}

	@SuppressWarnings("deprecation")
	public WebDriver getDriver() {
		if (driver == null) {

			BrowserType browser = BrowserType.valueOf(properties.getProperty("browserType"));
			switch (browser) {
			case IE:
				driver = new InternetExplorerDriver();
				break;

			case FIREFOX:

				driver = new FirefoxDriver();
				break;

			case CHROME:

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				break;

			default:
				driver = new ChromeDriver();

				break;
			}
			driver.manage().window().maximize();
			driver.get(getUrl());
		}
		return driver;
	}

	public String getUrl() {
		return properties.getProperty("url");
	}

}
