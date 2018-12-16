import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Utils.CommonFunctions;

public class TestBase {
	WebDriver driver;
	public TestBase(WebDriver driver2) {
		this.driver=driver2;
	}

	@BeforeTest
	public void setBrowser() {
		CommonFunctions Utility = new CommonFunctions(driver);
		Utility.setDriverPath();
		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
		ChromeDriverService srvc = builder
				.usingDriverExecutable(new File("C:\\chromedriver.exe"))
				.usingPort(63534).build();
		
		try {
			srvc.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Execute your test-script commands
		driver = new ChromeDriver(srvc,options);
		driver.manage().deleteAllCookies();
		driver.get("https://www.cleartrip.com/");
	}
	
	@AfterTest
	public void closeApplication()
	{
		driver.quit();
		
	}

	
}
