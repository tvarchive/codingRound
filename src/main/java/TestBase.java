package Testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.PlatformUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()  {
		prop = new Properties();	
		
		try {
		 FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java"
					+ "/config/config.properties");
		 prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public void waitFor(int durationInMilliSeconds) {
	        try {
	            Thread.sleep(durationInMilliSeconds);
	        } catch (InterruptedException e) {
	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	        }
	    }

	// Related For Browser and URl Laumching
	public static void BrowserUrllaunch(){	
		setDriverPath();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();	
	}
	
    @SuppressWarnings("restriction")
	public static  void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
            
        }
   
    }
}
