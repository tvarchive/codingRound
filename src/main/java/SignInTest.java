
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jna.Platform;

public class SignInTest {

	WebDriver driver;
	ChromeOptions options;
	WebDriverWait oWait;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		setDriverPath();
		driver = new ChromeDriver(options);

		driver.get("https://www.cleartrip.com/");

		oWait = new WebDriverWait(driver, 1000);
		oWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Your trips"))));

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();
		driver.switchTo().frame(1);

		driver.findElement(By.id("signInButton")).click();
		oWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("errors1"))));
		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		driver.quit();
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	private void setDriverPath() {
		if (Platform.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (Platform.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (Platform.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

}
