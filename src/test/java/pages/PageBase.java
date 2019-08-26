package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import supportlibraries.ReusableLibrary;
import supportlibraries.ScriptHelper;

public class PageBase extends ReusableLibrary {
	
	
	public Select select;

	public PageBase(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	public void click(WebElement element) {
		if (isElementPresent(element))
			element.click();
	}

	public boolean isElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
		if (ele != null)
			return true;
		return false;
	}

	public WebElement waitForElement(WebElement ele, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void selectValueFromOptionsList(WebElement ele, String value) {
		List<WebElement> options = ele.findElements(By.tagName("li"));
		boolean found = false;

		for (WebElement option : options) {
			if (option.getText().toLowerCase().contains(value.toLowerCase())) {
				click(option);
				found = true;
				break;
			}
		}

		if (!found) {
			Assert.fail(value +" is not available");
		}
	}
	
	public void selectByVisibleText(WebElement ele, String value) {
		new Select(ele).selectByVisibleText(value);
	}
	
	public void switchToiFrameById(WebElement ele) {
		driver.switchTo().frame(ele);
	}
	

}
