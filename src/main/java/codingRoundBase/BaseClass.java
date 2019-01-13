package codingRoundBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.PlatformUtil;

public class BaseClass   {
	 public static WebDriver driver;
	 

	public static void setDriverPath() {
	        if (PlatformUtil.isMac()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver");
	        }
	        if (PlatformUtil.isWindows()) {
	        	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	        	driver = new ChromeDriver();
	        	driver.manage().window().maximize();
	        	         }
	        if (PlatformUtil.isLinux()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	        }

}}
