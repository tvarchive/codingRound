import com.sun.javafx.PlatformUtil;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {
	public WebDriver driver;

	@Test
	public void shouldBeAbleToSearchForHotels() {
		setDriverPath();
		closeNotification();

		driver.manage().window().maximize();
		driver.get("https://www.cleartrip.com/");
		driver.findElement(By.linkText("Hotels")).click();
		driver.findElement(By.id("Tags")).sendKeys("Indiranagar, Bangalore");

		WebElement travellerSelection = driver.findElement(By.id("travellersOnhome"));
		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");

		driver.findElement(By.id("SearchHotelsButton")).click();

		driver.quit();

	}

	private void closeNotification() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);

	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
	}

}
