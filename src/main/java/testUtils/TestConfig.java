package testUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class TestConfig {
	WebDriver driver;

	@BeforeTest
	public void setUpSystem() {
		System.out.println("Executing before Test");
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars");
		setDriverPath();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	@AfterTest
	public void afterTest() throws IOException {
		System.out.println("Executing after Test");
		driver.quit();
		if (PlatformUtil.isWindows()) {
			Process process = Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			process.destroy();
		}

	}

	public WebDriver getdriver() {
		return driver;

	}


	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", ".//chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", ".//chromedriver_linux");
		}
	}

}
