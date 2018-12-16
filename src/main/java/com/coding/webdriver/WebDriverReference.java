package com.coding.webdriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;

public class WebDriverReference {
	private static ThreadLocal<WebDriver> threaddriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> browsername = new ThreadLocal<String>();
	private static ThreadLocal<Properties> commonProp = new ThreadLocal<Properties>();
	static FileReader reader;

	public WebDriver getDriver() {
		return threaddriver.get();
	}

	public void setWebDriver(WebDriver driver, String browser) {
		threaddriver.set(driver);
		browsername.set(browser);
	}

	public String getBrowser() {
		return browsername.get();
	}

	public void removeDriver() {
		threaddriver.remove();
		browsername.remove();
	}

	public void setProperty(String filePath) throws IOException {
		commonProp.set(new Properties());
		reader = new FileReader(filePath);
		commonProp.get().load(reader);
		BasicConfigurator.configure();
	}

	public static Properties getProperties() {
		return commonProp.get();
	}
}
