import com.testvagrant.utils.ApplicationLibrary;
import com.testvagrant.utils.PublicLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

	WebDriver driver;
	PublicLibrary library;

	@BeforeTest
	public void setUpTestObjects() {
		library = new ApplicationLibrary();
		library.initialiseBrowser("chrome");
		this.driver = library.getDriverInstance();
	}

	@AfterTest
	public void closingBrowserWindow() {
		library.closeWebBrowser();
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		driver.get("https://www.cleartrip.com/");

		// Navigating to signIn popup
		By byLocYourTripsLink = library.getByObject("yourTrips");
		library.waitForElementToDisplay(byLocYourTripsLink);
		driver.findElement(byLocYourTripsLink).click();
		driver.findElement(library.getByObject("signInLink")).click();

		// Switching to iframe in Login popup
		By byLocLoginIframe = library.getByObject("loginFrame");
		driver.switchTo().frame(driver.findElement(byLocLoginIframe));

		// CLick on Sign in button before entering login details for getting login error
		By byLocSignInBtn = library.getByObject("signInBtn");
		library.waitForElementToDisplay(byLocSignInBtn);
		driver.findElement(byLocSignInBtn).click();
		
		// Verify Error message after login without credentialss
		String errors1 = driver.findElement(library.getByObject("errorMsg")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));

	}

}
