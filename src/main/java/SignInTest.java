import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

	WebDriver driver;
	WebDriverManager webDriverManager;
	ObjectRepositoryManager objectRepo;

	@BeforeTest
	public void setup() throws IOException {
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		objectRepo = new ObjectRepositoryManager("ObjectRepository.properties");
	}

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		driver.get("https://www.cleartrip.com/");
		waitFor(2000);		
		driver.findElement(By.linkText(objectRepo.getObjectLocatorValue("yourtripdropdown"))).click();
		waitFor(2000);
		driver.findElement(By.id(objectRepo.getObjectLocatorValue("signInClick"))).click();
		waitFor(2000);
		// switching to frames
		driver.switchTo().frame(objectRepo.getObjectLocatorValue("frameName")); 																
																				
		waitFor(2000);
		driver.findElement(By.id(objectRepo.getObjectLocatorValue("signInButton"))).click();
		waitFor(2000);
		String errors1 = driver.findElement(By.id(objectRepo.getObjectLocatorValue("errorsId"))).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));

	}

	@AfterTest
	public void tearDown() {

		webDriverManager.closeDriver();
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

}
