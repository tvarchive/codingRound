import java.io.File;
import java.io.IOException;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageActions.SignInPage;
import Utils.CommonFunctions;

public class SignInTest {
    WebDriver driver;
    
    @BeforeTest
	public void setBrowser() {
		CommonFunctions Utility = new CommonFunctions(driver);
		Utility.setDriverPath();
		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
		ChromeDriverService srvc = builder
				.usingDriverExecutable(new File("C:\\chromedriver.exe"))
				.usingPort(63534).build();
		
		try {
			srvc.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Execute your test-script commands
		driver = new ChromeDriver(srvc,options);
		driver.manage().deleteAllCookies();
	}
    
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	SignInPage sp=new SignInPage(driver);
    	driver.get("https://www.cleartrip.com/");
    	sp.chkErrorMsg();
    }

}
