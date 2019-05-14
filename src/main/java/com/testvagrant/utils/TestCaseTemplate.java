package com.testvagrant.utils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestCaseTemplate {

	protected WebDriver driver;
	protected ApplicationLibrary library;
	protected CustomLogger logger;
	protected Map<String, String> exceldata;

	/**
	 * performs test objects setup like driver, excel data etc.,
	 */
	@BeforeTest
	public void setUpTestObjects() {
		logger = new CustomLogger();
		logger.log("Executing test setup");
		library = new ApplicationLibrary();
		library.initialiseBrowser(MiscConstants.BROWSER_CHROME);
		this.driver = library.getDriverInstance();
		driver.get(PropertFileManager.getPropertyFromFile("appUrl", MiscConstants.PROP_MISC));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		exceldata = ExcelFileManager.getTestCaseDate(this.getClass().getSimpleName());
	}

	/**
	 * closed the Web Browser
	 */
	@AfterTest
	public void closingBrowserWindow() {
		logger.log("Executing test clean up");
		library.closeWebBrowser();
	}
}
