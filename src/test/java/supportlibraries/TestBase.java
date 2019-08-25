package supportlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {

	protected WebDriver driver;

	protected Properties properties;

	protected ScriptHelper scriptHelper;

	protected static String globalSettingsPath = "settings.gradle";

	// initialize the driver
	@BeforeClass(alwaysRun = true)
	public void testClassSetup() {
		System.out.println("before class");
		// properties = getPropInstance();
		initializeDriver();
		initializeTestScript();
	}

	// launch browser and wait until page load.
	@BeforeMethod(alwaysRun = true)
	public void testMethodSetup() {
		if (driver == null)
			initializeDriver();
		launchURl();
		waitForPageLoad(1000);
	}

	private void initializeDriver() {
		driver = WebDriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private void initializeTestScript() {
		this.scriptHelper = new ScriptHelper(driver);
	}

	public void launchURl() {
		// properties = getPropInstance();
		String uriStr = "https://www.cleartrip.com/";
		driver.get(uriStr);
	}

	public void waitForPageLoad(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	public Properties getPropInstance() {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(globalSettingsPath));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException while loading the Settings file");
		} catch (IOException e) {
			System.out.println("IOException while loading the Global Settings file");
		}

		return properties;
	}

	@AfterClass(alwaysRun = true)
	public void testClassTearDown() {
		driver.quit();
	}

}
