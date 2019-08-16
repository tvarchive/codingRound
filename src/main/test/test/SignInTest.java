package test;
import com.sun.javafx.PlatformUtil;

import common.CommonMethods;
import common.DriverFactory;
import pom.LoginPage;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest {
	
	
	static WebDriver driver;
	LoginPage loginPage; 
	
	Properties prop = CommonMethods.prop;
	String userName = prop.getProperty("userName");
	String password = prop.getProperty("password");

	@BeforeClass
	public void setUp(){
		driver = DriverFactory.setUpDriver();
		loginPage = new LoginPage(driver);
	}
	
	@Test(groups = "Login")
	private void invalidlogin() throws Exception {
		loginPage.loginerror();
		Assert.assertEquals(driver.findElement(By.id("errors1")).getText(),"There were errors in your submission");
	}
	
	
	@AfterClass
	public void tearDown(){
		DriverFactory.shutDownDriver();
	}

}
