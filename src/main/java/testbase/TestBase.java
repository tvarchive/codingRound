package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.CommonFunctions;

public class TestBase {

	public static WebDriver driver;
	protected ChromeOptions options;
	public static Properties oProp;

	protected void initialize() throws IOException {
		File propFile = new File(System.getProperty("user.dir") + "/src/main/java/config/config.properties");
		oProp = new Properties();
		oProp.load(new FileInputStream(propFile));
	}

	@BeforeMethod
	protected void setup() throws IOException {
		initialize();
		CommonFunctions.setDriverPath();
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get(oProp.getProperty("URL"));
	}

	@AfterMethod
	protected void tearDown() {
		driver.quit();
	}
}
