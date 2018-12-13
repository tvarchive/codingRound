import com.sun.javafx.PlatformUtil;

import PageObjects.SignInTestPageObject;
import Utils.DriverFactoy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends SignInTestPageObject {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		clickOnTripsButton();
		clickOnSignInButton();

		String errors1 = signInErrorMsg();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
	}

}
