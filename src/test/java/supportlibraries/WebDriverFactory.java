package supportlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class WebDriverFactory {

	private static Properties properties;
	private static String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\";
	private static String propertySettingsPath = "gradle.properties";

	@SuppressWarnings("deprecation")
	public static WebDriver getDriver() {
		WebDriver driver = null;

		properties = getOSInstance();
		String osName = properties.getProperty("platform.name").toLowerCase();

		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

		if (osName.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		}
		if (osName.contains("mac")) {
			chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--verbose"));
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
		}
		if (osName.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver_linux");
		}

		driver = new ChromeDriver(chromeCapabilities);

		return driver;
	}

	public static Properties getOSInstance() {

		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(driverPath + propertySettingsPath));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException while loading the Settings file");
		} catch (IOException e) {
			System.out.println("IOException while loading the Global Settings file");
		}

		return properties;
	}

}
