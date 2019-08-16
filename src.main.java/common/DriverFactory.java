package common;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sun.javafx.PlatformUtil;

import utils.ReadProperty;


public class DriverFactory {
	
	static String pfileName = "global.properties";
	static Properties  prop = new ReadProperty().readPropertyFile(pfileName);
	
	static WebDriver driver;
	static String url = prop.getProperty("url");
	static String browser = prop.getProperty("browser");
	static int timeOut = Integer.parseInt(prop.getProperty("timeOut"));
	static int implecitWait = Integer.parseInt(prop.getProperty("implecitWait"));

	
	public static WebDriver setUpDriver(){
		if (browser.equals("chrome") && PlatformUtil.isWindows() ){
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(implecitWait, TimeUnit.SECONDS);
			//driver.manage().timeouts().setScriptTimeout(implecitWait, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		
		else if (browser.equals("chrome") && PlatformUtil.isMac() ){
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(implecitWait, TimeUnit.SECONDS);;
			driver.manage().window().maximize();
		}
		return driver;
	}
	
	public static void shutDownDriver(){
		driver.close();
		driver.quit();
	}
	public static WebDriver getDriver(){
		return driver;
	}
	
	

}
