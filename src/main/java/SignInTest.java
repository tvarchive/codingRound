
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.TestBase;
import utils.CommonFunctions;

public class SignInTest extends TestBase {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		WebElement YourTrips = driver.findElement(By.linkText("Your trips"));
		CommonFunctions.explicitWaitForElement(YourTrips, 100, driver);

		CommonFunctions.clickOnElement(YourTrips);

		WebElement signIn = driver.findElement(By.id("SignIn"));

		CommonFunctions.clickOnElement(signIn);

		CommonFunctions.switchToFrame(1, driver);

		WebElement signInbtn = driver.findElement(By.id("signInButton"));
		CommonFunctions.clickOnElement(signInbtn);

		WebElement errors = driver.findElement(By.id("errors1"));
		CommonFunctions.explicitWaitForElement(errors, 200, driver);

		String errors1 = errors.getText();

		Assert.assertTrue(errors1.contains("There were errors in your submission"));

	}

}
