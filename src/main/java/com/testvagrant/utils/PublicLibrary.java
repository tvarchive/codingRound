package com.testvagrant.utils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

public class PublicLibrary {
	private WebDriver driver;

	/**
	 * Initializes driver path
	 */
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

	/**
	 * Closes the browser window if open
	 */
	public void closeWebBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Initializes driver object with required browser driver.
	 * 
	 * @param requiredBrowserName : Name of required browser
	 */
	public void initialiseBrowser(String requiredBrowserName) {

		setDriverPath();

		if (driver != null) {
			switch (requiredBrowserName) {
			case "chrome": {
				driver = new ChromeDriver();
			}
			case "firefox": {
				driver = new FirefoxDriver();
			}
			case "ie": {
				driver = new InternetExplorerDriver();
			}
			default: {
				driver = new ChromeDriver();
			}
			}
		}
	}

	/**
	 * return a valid driver object to user
	 * 
	 * @return : driver object
	 */
	public WebDriver getDriverInstance() {
		if (driver == null) {
			initialiseBrowser("chrome");
		}
		return driver;
	}

	/**
	 * Waiting for an element to be present dynamically
	 * 
	 * @param byLocater : By object of required element
	 */
	public void waitForElementToDisplay(By byLocater) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(byLocater));
		wait.pollingEvery(1, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

	}

	/**
	 * Wait for defined number of seconds
	 * 
	 * @param durationInMilliSeconds : Seconds of time for which the program has to
	 *                               wait before executing the next command.
	 */
	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**
	 * Check whether an element is present in HTML dom of the application. Note:
	 * This method doesn't verify element is Displayed/Enabled.
	 * 
	 * @param by : By locator of the element
	 * @return : boolean result of presence of element in Application DOM
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
