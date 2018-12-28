package testbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import utils.CommonFunctions;

public class TestBase {

	public static WebDriver driver;
	protected ChromeOptions options;

	@BeforeMethod
	protected void setup() {
		CommonFunctions.setDriverPath();
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.cleartrip.com/");
	}

	@AfterMethod
	protected void tearDown() {
		driver.quit();
	}
}
