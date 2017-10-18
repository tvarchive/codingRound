package com.testvagrant.codinground.initializations;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverCreator {

	private static String browser;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriver getDriver(String browserName) {
        browser = browserName;
     
        
            if (browser.equalsIgnoreCase("firefox")) {
                return getFirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                return getChromeDriver(System.getProperty("user.dir") + File.separator+ "chromedriver.exe");
            }
      
        return new FirefoxDriver();
    }

    @SuppressWarnings("deprecation")
	private static WebDriver getChromeDriver(String driverpath) {
        System.setProperty("webdriver.chrome.driver", driverpath);
        capabilities.setJavascriptEnabled(true);
        return new ChromeDriver(capabilities);
    }


   
    private static WebDriver getFirefoxDriver() {
        return new FirefoxDriver();
    }
    
    public WebDriver setRemoteDriver(String browser) {
        DesiredCapabilities cap = null;
        DriverCreator.browser = browser;
        if (browser.equalsIgnoreCase("firefox")) {
            cap = DesiredCapabilities.firefox();
        } else if (browser.equalsIgnoreCase("chrome")) {
            cap = DesiredCapabilities.chrome();
        } else if (browser.equalsIgnoreCase("Safari")) {
            cap = DesiredCapabilities.safari();
        } else if ((browser.equalsIgnoreCase("ie"))
                || (browser.equalsIgnoreCase("internetexplorer"))
                || (browser.equalsIgnoreCase("internet explorer"))) {
            cap = DesiredCapabilities.internetExplorer();
        }
        String seleniuhubaddress = "http://127.0.0.0:4444/wd/hub";
        URL selserverhost = null;
        try {
            selserverhost = new URL(seleniuhubaddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        cap.setJavascriptEnabled(true);
        return new RemoteWebDriver(selserverhost, cap);
    }
	
}