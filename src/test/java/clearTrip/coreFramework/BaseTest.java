package clearTrip.coreFramework;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends Utility {

	// WebDriverManager dependency is added to avoid the overhead
	// downloading chromeDriver and matching it with Selenium and OS
	// put this into instance block to make drivers readily available before they
	// are required
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setDriver() {

		// to accomodate other browsers in future, presently we are working with just
		// chrome
		if (true) {
			driver = setupForChrome();
		}
	}

	// launch URL as it's same across all our test cases here, we can parameterize
	// if multiple URL's are to be hit

	@BeforeMethod(dependsOnMethods = "setDriver")
	public void launchWebsite() {
		driver.get("https://www.cleartrip.com/");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}