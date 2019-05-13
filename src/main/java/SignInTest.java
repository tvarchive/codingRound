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
		library.waitFor(2000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();

		driver.findElement(By.id("signInButton")).click();

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));

	}

}
