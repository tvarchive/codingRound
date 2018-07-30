package test.java.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * FWUtils
 * 
 * Framework Utility files consisting of utility methods to be used in page
 * objects, tests for test execution
 *
 */
public class FWUtils {

	/**
	 * method waitForExpCondition Waits for given time in seconds until expected
	 * condition gets fulfilled
	 * 
	 * TBD
	 * 
	 * @param driver
	 */
	public void waitForExpCondition(WebDriver driver) {
		WebDriverWait wdWait = new WebDriverWait(driver, 10);
	}

	/**
	 * method waitFor
	 * 
	 * Waits for given time in milliseconds
	 * 
	 * @param durationInMilliSeconds
	 */
	public void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**
	 * method isElementPresent Checks for presence of WebElement on Web page
	 * 
	 * @param wd
	 * @param by
	 * @return boolean
	 */

	public boolean isElementPresent(WebDriver wd, By by) {
		try {
			wd.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
