package test.java.framework;

import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import org.openqa.selenium.WebDriver;

/**
 * BaseTest.java
 * 
 * Contains Test suite and test class methods
 * to perform pre & post test activities
 * 
 * Linked with to TestListener class
 */


@Listeners(TestListener.class)

public class BaseTest {

	public WebDriver driver = null;
	public static HashMap<String, String> testConfigData = null;

	/**
	 * method - beforeSuite 
	 * Reads test configuration data
	 * 
	 * @return void
	 * 
	 */
	@BeforeSuite()
	public void beforeSuite() {
		TestConfig testConfig = new TestConfig();
		testConfigData = testConfig.getConfigData();
		Reporter.log("{INFO] Test Configuration Data: " + testConfigData, true);
	};

	/**
	 * method - beforeClass
	 * Call FWDriver class to initialize Selenium WebDriver 
	 * based on provided test configuration data
	 * 
	 * @return void
	 */
	@BeforeClass
	public void beforeClass() {
		FWDriver fwDriver = new FWDriver();
		if (testConfigData != null)
			driver = fwDriver.init(testConfigData);
	}

	/**
	 * method - afterClass
	 * 
	 * Teardown Selenium Webdriver
	 * 
	 * @return void
	 * 
	 */
	@AfterClass
	public void afterClass() {
		if (driver != null)
			driver.quit();
	}
}
