package com.coding.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.coding.browser.LaunchBrowser;
import com.coding.objectrepository.FlightBookingOR;
import com.coding.uicontroller.Loggers;
import com.coding.uicontroller.Wait;
import com.coding.webdriver.WebDriverReference;

public class BaseExecution extends WebDriverReference {
	Wait wait = new Wait();
	private LaunchBrowser launchBrowser;
	private String browserType = null;
	private String version = null;
	private String testName = null;
	private String testDescription = null;
	String filePath = System.getProperty("user.dir") + File.separator + "TestData" + File.separator
			+ "config.properties";

	@BeforeSuite(alwaysRun = true)
	public void setup() throws IOException {
		Loggers.setLogger();
		setProperty(filePath);
	}

	@BeforeTest
	@Parameters({ "BROWSERTYPE", "VERSION" })
	public void createTestResult(String BROWSERTYPE, String VERSION) {
		this.browserType = BROWSERTYPE;
		this.version = VERSION;
		if (!browserType.isEmpty()) {
			try {
				Loggers.info("Starting Browser with config--->" + browserType + "-" + version);
				setupDriver(browserType, version);
			} catch (Exception e) {
				Loggers.error(e.getMessage());
			}
		}
	}

	@AfterTest()
	public void endTestResult() {
		try {
			Loggers.info("QUIT BROWSER :" + getBrowser());
			closeAllOpenedWindows();
			removeDriver();
		} catch (Exception e) {
			Loggers.error("Issue in Closing Test Result");
		}
	}

	@BeforeMethod()
	public void start(Method call) {
		testName = call.getName();
		testDescription = call.getAnnotation(Test.class).description();
		Loggers.info("Starting Test " + testName + " -- With Description-- " + testDescription);
	}

	@AfterMethod()
	public void end() throws Exception {
		try {
			// TODO:
		} catch (Throwable e) {
			Loggers.info("Page load issue Started force Login-----" + e);

		}
	}

	@AfterSuite(alwaysRun = true)
	public void TerminateTest() throws IOException {
		Loggers.info("Closing TestSuite.....");
	}

	public void setupDriver(String browserType, String version) {
		try {
			launchBrowser = new LaunchBrowser();
			launchBrowser.setDriver(browserType, version);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeAllOpenedWindows() {
		Loggers.info("Closing All Instance of the Browser");
		getDriver().quit();
		removeDriver();
	}

	public void closeCurrentWindow() {
		Loggers.info("Closing Current Browser Instance");
		getDriver().close();
	}

	public void assertEqual(Object actualValue, Object expectedValue) {
		Assert.assertEquals(actualValue, expectedValue);
		Loggers.info("--Actual : " + actualValue + " -- Expected : " + expectedValue);
	}
	
	public void switchToHomePage() throws Exception {
		try {
			getDriver().navigate().to(getProperties().getProperty("applicationUrl"));
			wait.waitforPageLoad();
			wait.waitForElementToDisplay(FlightBookingOR.flightLink);
			Loggers.info("Switch back to Home page");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
