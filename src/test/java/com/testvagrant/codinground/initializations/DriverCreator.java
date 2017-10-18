package com.testvagrant.codinground.initializations;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.javafx.PlatformUtil;


public class DriverCreator {

	private static String browser;
    private static DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriver getDriver(String browserName) {
        browser = browserName;
        
        if (browser == "chrome"){
        	 if (PlatformUtil.isMac()) {
             	return getChromeDriver(System.getProperty("user.dir") + File.separator+ "Resources"+ File.separator+ "chromedriver");
             }
             if (PlatformUtil.isWindows()) {
             	return getChromeDriver(System.getProperty("user.dir") + File.separator+ "Resources"+ File.separator+ "chromedriver.exe");
             }
             if (PlatformUtil.isLinux()) {
             	return getChromeDriver(System.getProperty("user.dir") + File.separator+ "Resources"+ File.separator+ "chromedriver_linux");
             }    	
        }
        
       
       return new FirefoxDriver(); // if something goes wrong, FF is default browser
    }

    @SuppressWarnings("deprecation")
	private static WebDriver getChromeDriver(String driverpath) {
        System.setProperty("webdriver.chrome.driver", driverpath);
        capabilities.setJavascriptEnabled(true);
        return new ChromeDriver(capabilities);
    }


 
	
}