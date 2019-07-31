import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.sun.javafx.PlatformUtil; 

public class WebDriverManager {
	
 private WebDriver driver;
 private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
 

 
 public WebDriver getDriver() {
 if(driver == null) driver = createDriver();
 return driver;
 }
 
 private WebDriver createDriver() {
    
	 setDriverPath();
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 return driver;
 }
 
  
 
 private void setDriverPath() {
     if (PlatformUtil.isMac()) {
         System.setProperty(CHROME_DRIVER_PROPERTY, "chromedriver");
     }
     if (PlatformUtil.isWindows()) {
         System.setProperty(CHROME_DRIVER_PROPERTY, "chromedriver.exe");
     }
     if (PlatformUtil.isLinux()) {
         System.setProperty(CHROME_DRIVER_PROPERTY, "chromedriver_linux");
     }
 }
 
 public void closeDriver() {

 driver.quit();
 }
 
}