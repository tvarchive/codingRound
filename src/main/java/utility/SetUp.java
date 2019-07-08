package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;

public class SetUp {

	public WebDriver driver = null;

	public void mysetUp() throws IOException {

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//utility//global.properties");
		p.load(fi);

		System.out.println(p.getProperty("browser"));

		if (p.getProperty("browser").contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", "//home//amura//SeleniumStuffs//geckodriver");
			driver = new FirefoxDriver();
		} else if (p.getProperty("browser").contains("chrome")) {

			System.setProperty("webdriver.chrome.driver", "//home//amura//SeleniumStuffs//chromedriver");

			driver = new ChromeDriver();

			try {
				driver.get(p.getProperty("url"));
			} catch (TimeoutException e2) {
				// TODO Auto-generated catch block
				// Log.error("Catching timeout exception");
				driver.navigate().refresh();
			}
			driver.manage().window().maximize();
		}
	}

}