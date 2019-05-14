package com.testvagrant.utils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

public class PublicLibrary {
	private WebDriver driver;
	CustomLogger logger;

	public PublicLibrary() {
		logger = new CustomLogger();
	}

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
		logger.log("Closing browser");
	}

	/**
	 * Initializes driver object with required browser driver.
	 * 
	 * @param requiredBrowserName : Name of required browser
	 */
	public void initialiseBrowser(String requiredBrowserName) {

		setDriverPath();

		if (driver == null) {
			switch (requiredBrowserName) {
			case MiscConstants.BROWSER_CHROME: {
				driver = new ChromeDriver();
				break;
			}
			case MiscConstants.BROWSER_FIREFOX: {
				// driver = new FirefoxDriver();
				break;
			}
			case MiscConstants.BROWSER_INTERNETEXPLORER: {
				// driver = new InternetExplorerDriver();
				break;
			}
			default: {
				driver = new ChromeDriver();
			}
			}
		}
		driver.manage().window().maximize();
		logger.log(requiredBrowserName + " opened and window maximized");
	}

	/**
	 * return a valid driver object to user
	 * 
	 * @return : driver object
	 */
	public WebDriver getDriverInstance() {
		if (driver == null) {
			initialiseBrowser(MiscConstants.BROWSER_CHROME);
		}
		return driver;
	}

	/**
	 * Waiting for an element to be present dynamically
	 * 
	 * @param byLocater : By object of required element
	 */
	public void waitForElementToDisplay(By byLocater) {
		logger.log("Waiting for element to be present...");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(byLocater));
		wait.pollingEvery(1, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		logger.log("Found element in DOM...");
	}

	/**
	 * Wait for defined number of seconds
	 * 
	 * @param durationInMilliSeconds : Seconds of time for which the program has to
	 *                               wait before executing the next command.
	 */
	public void waitFor(int durationInMilliSeconds) {
		try {
			logger.log("Waiting for... " + (durationInMilliSeconds / 1000) + " seconds");
			Thread.sleep(durationInMilliSeconds);
			logger.log("Wait completed...");
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

	/**
	 * Getting By object dynamically from properties file.
	 * 
	 * @param objectKey : key name of locator
	 * @return : By object of locator
	 */
	public By getByObject(String objectKey) {

		String locValue = PropertFileManager.getPropertyFromFile(objectKey, MiscConstants.PROP_OR);
		if (locValue.startsWith("By.id(")) {
			locValue = locValue.split("\\(")[1];
			return By.id(locValue);
		} else if (locValue.startsWith("By.className(")) {
			locValue = locValue.split("\\(")[1];
			return By.className(locValue);
		} else if (locValue.startsWith("By.xpath(")) {
			locValue = locValue.split("\\(")[1];
			return By.xpath(locValue);
		} else if (locValue.startsWith("By.cssSelector(")) {
			locValue = locValue.split("\\(")[1];
			return By.cssSelector(locValue);
		} else if (locValue.startsWith("By.linkText(")) {
			locValue = locValue.split("\\(")[1];
			return By.linkText(locValue);
		} else
			return null;
	}

	/**
	 * Waits for an element to appear and Clicking on it
	 * 
	 * @param byLocator : By locator of the element
	 */
	public void clickElement(By byLocator) {
		waitForElementToDisplay(byLocator);
		driver.findElement(byLocator).click();
	}

	/**
	 * Waits for an element to appear and setting text to the element. Sets empty
	 * text if messageText is null or empty
	 * 
	 * @param byLocator   : By locator of the element
	 * @param messageText : Text to be set to element
	 */
	public void setTextToElement(By byLocator, String messageText) {
		waitForElementToDisplay(byLocator);
		WebElement element = driver.findElement(byLocator);
		element.clear();
		element.sendKeys(messageText == null ? "" : messageText);
	}

	/**
	 * Switching the driver to iframe
	 * 
	 * @param byLocatorOfFrame : By locater of iframe
	 */
	public void switchToFrame(By byLocatorOfFrame) {
		waitForElementToDisplay(byLocatorOfFrame);
		driver.switchTo().frame(driver.findElement(byLocatorOfFrame));
	}
}
