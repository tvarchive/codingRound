package com.codinground.driverutiils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.codinground.ioutils.PropertiesFileUtil;
import com.sun.javafx.PlatformUtil;

public class DriverFactory {

	    protected static WebDriver driver;
        private static ChromeOptions options;

	    public DriverFactory() {
	        initialize();
	    }

	    public void initialize() {
	    	
	    	if (driver == null)
	            createNewDriverInstance();
	    }

	    private void createNewDriverInstance() {
	    	String browser = new PropertiesFileUtil().readProperty("browser");
	    	String driverPathWin =new PropertiesFileUtil().readProperty("driverPathWin");
	    	String driverPathMac = new PropertiesFileUtil().readProperty("driverPathMac");
	    	String driverPathLinux = new PropertiesFileUtil().readProperty("driverPathLinux");
	        if (browser.equals("firefox")) {
	            driver = new FirefoxDriver();
	        } else if 
	        	 (browser.equals("chrome")) {
	        	 if (PlatformUtil.isMac()) {
	                 System.setProperty("webdriver.chrome.driver", driverPathMac);
	             }
	             if (PlatformUtil.isWindows()) {
	                 System.setProperty("webdriver.chrome.driver", driverPathWin);
	             }
	             if (PlatformUtil.isLinux()) {
	                 System.setProperty("webdriver.chrome.driver", driverPathLinux);
	             }
	                options = new ChromeOptions();
	                options.addArguments("--disable-notifications");
		            driver = new ChromeDriver(options);
	             }
	        }
	    
	   public void launchUrl() {
		 String url = new PropertiesFileUtil().readProperty("url");  
		 driver.get(url);
		 
	   }
	    public WebDriver getDriver() {
	        return driver;
	    }

	    public void destroyDriver() {
	        driver.quit();
	        driver = null;
	    }		
	
	
	
	
	
}
