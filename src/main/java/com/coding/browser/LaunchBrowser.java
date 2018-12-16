package com.coding.browser;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.coding.uicontroller.Loggers;
import com.coding.uicontroller.Wait;
import com.coding.webdriver.WebDriverReference;
import com.sun.javafx.PlatformUtil;

public class LaunchBrowser extends WebDriverReference {
	DesiredCapabilities capability = null;
	String resources = "Resource";
	String directory = System.getProperty("user.dir");
	Wait wait = new Wait();
	WebDriver driver;

	public void setDriver(String browserType, String version) throws Exception {
		if (browserType.contains("CHROME")) {
			Loggers.info("Chrome_" + version + " browser is selected");
			if (PlatformUtil.isWindows()) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator
						+ resources + File.separator + "chromedriver.exe");
			}
			if (PlatformUtil.isMac()) {
				System.setProperty("webdriver.chrome.driver",
						directory + File.separator + resources + File.separator + "chromedriver");
			}
			if (PlatformUtil.isLinux()) {
				System.setProperty("webdriver.chrome.driver",
						directory + File.separator + resources + File.separator + "chromedriver_linux");
			}
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--disable-notifications");
			options.addArguments("disable-infobars");
			options.addArguments("disable-extensions");
			options.addArguments("disable-popup-blocking");
			options.addArguments("--start-maximized");
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability("platform", Platform.ANY);
			options.setCapability("browserName", "chrome");
			options.setCapability("takesScreenshot", true);
			options.setCapability("handlesAlerts", true);
			options.setCapability("browserTimeout", 9000000);
			driver = new ChromeDriver(options);
		} else if (browserType.equals("FIREFOX")) {
			Loggers.info("Firefox_" + version + " browser is selected");
			if (PlatformUtil.isWindows()) {
				System.setProperty("webdriver.gecko.driver",
						directory + File.separator + resources + File.separator + "geckodriver.exe");
			}
			if (PlatformUtil.isMac()) {
				System.setProperty("webdriver.gecko.driver",
						directory + File.separator + resources + File.separator + "geckodriver");
			}
			if (PlatformUtil.isLinux()) {
				System.setProperty("webdriver.gecko.driver",
						directory + File.separator + resources + File.separator + "geckodriver_linux");
			}
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
			firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			firefoxoptions.setAcceptInsecureCerts(true);
			firefoxoptions.setProfile(firefoxProfile);
			firefoxoptions.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			driver = new FirefoxDriver(firefoxoptions);
		} else if (browserType.equals("IE")) {
			// TODO:
		}
		setWebDriver(driver, browserType + "_" + version);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		getDriver().get(getProperties().getProperty("applicationUrl"));
		wait.waitforPageLoad();
	}
}
