package com.coding.uicontroller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.coding.webdriver.WebDriverReference;
import com.google.common.base.Function;

public class Wait extends WebDriverReference {
	protected WebElement element;
	protected List<WebElement> elements = null;

	public WebElement findElement(final By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		try {
			return wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					element = driver.findElement(locator);
					return element.isDisplayed() ? element : null;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}

	public List<WebElement> findElements(final By locator) {
		elements = getDriver().findElements(locator);
		return elements.size() > 0 ? elements : null;
	}

	public void waitForElementToDisplay(By locator) throws Exception {
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(getDriver(), 60);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			Loggers.error("Waiting for element to appear on DOM : " + locator);
			throw new Exception("Waiting for element to appear on DOM But not found : " + locator);
		}
	}

	public void waitforPageLoad() {
		try {
			sleep(5000);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			String js3 = "function getPageState() {" + "return document.readyState;" + "}; " + "return getPageState()";
			String pageState = "";
			int counter = 0;
			while (!pageState.equals("complete") && counter < 7) {
				pageState = (String) executor.executeScript(js3);
				sleep(6000);
				counter++;
				Loggers.info("pageState is" + pageState + "with counter" + counter);
			}
		} catch (Exception e) {
			Loggers.error("Waiting for element to appear on wait waitforPageLoad() DOM");
		}
	}

	public boolean isElementClickable(WebElement element) {
		WebDriverWait waitforClick = new WebDriverWait(getDriver(), 20);
		try {
			waitforClick.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			Loggers.error("Web Element Not Clickable after 20 sec wait : " + element);
			return false;
		}
	}

	public void sleep(int sleepTime) throws InterruptedException {
		Thread.sleep(sleepTime);
	}
}
