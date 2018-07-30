package test.java.framework;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

import com.sun.javafx.PlatformUtil;

/**
 * 
 * FWDriver.java
 * 
 * Contains methods to setup/initialize Selenium WebDriver provided with
 * parameters
 *
 */
public class FWDriver {

	/**
	 * method - init
	 * 
	 * initialize WebDriver instance based on 
	 * provided parameters
	 * 
	 * Current support: only in 'local'
	 * TBD: Add support for saucelabs | selenium-grid
	 * 
	 * 
	 * @param configData
	 * @return driver
	 */
	public WebDriver init(HashMap<String, String> configData) {

		String host = configData.get("host");

		// set parameter values based on host
		switch (host) {
		case "local": {
			if (configData.get("appUrl") != null) {
				return setDriverInLocal(configData.get("appUrl"), configData.get("browser"));
			} else {
				return setDriverInLocal(FWConstants.DEFAULT_APP_URL, "chrome");
			}
		}
		case "saucelabs": {
			return null;
		}
		default: {
			Reporter.log("[ERROR] Invalid host. Returning driver as NULL for host - " + host, true);
			return null;
		}
		}
	}

	/**
	 * method - setDriverInLocal
	 * 
	 * Set WebDriver in local environment
	 * 
	 * @param url 
	 * @param browser
	 * @return WebDriver
	 */
	private WebDriver setDriverInLocal(String url, String browser) {
		if (browser != null) {
			if (browser.equalsIgnoreCase("firefox")) {
				// setup firefox driver
				// not supported as of now
				return null;
			}
			// more browser options can be added as 'else if'
		}
		WebDriver wd = setChromeDriver();
		wd.get(url);
		return wd;
	}

	/**
	 * method chromeDriver
	 * 
	 * Setup chromedriver exe path and 
	 * initialize Selenium chrome driver
	 * 
	 * @return WebDriver
	 */
	private WebDriver setChromeDriver() {
		ChromeOptions options = new ChromeOptions();
    	options.addArguments("--start-maximized");

		setDriverPath();
		WebDriver wd = new ChromeDriver(options);
		return wd;
	}

	
	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_linux");
		}
	}
}
