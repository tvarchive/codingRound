package com.test.pageobjects;

import com.sun.javafx.PlatformUtil;
import com.test.services.CommonUtils;
import com.test.services.ServiceInitializer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HomePage {
	WebDriver driver = null;
	WebDriverWait wait;
	CommonUtils commonutils = new CommonUtils();

	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	@FindBy(linkText = "Hotels")
	public WebElement hotelLink;

	@FindBy(id = "Tags")
	public WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	public WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	public WebElement travellerSelection;

	public void shouldBeAbleToSearchForHotels() {

		commonutils.waitFor(5000);
		hotelLink.click(); //click on Hotels

		localityTextBox.sendKeys("Indiranagar, Bangalore");

		commonutils.waitFor(5000);
		WebElement whereDropDown = driver.findElement(By.id("ui-id-1"));
		wait.until(ExpectedConditions.visibilityOf(whereDropDown));
		List<WebElement> whereOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		System.out.println(whereOptions);
		whereOptions.get(3).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
		driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[4]/td[3]/a")).click();

		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();

	}

}
