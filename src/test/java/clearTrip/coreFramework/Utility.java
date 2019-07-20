package clearTrip.coreFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

// one single method library as we have very few methods as of now
//ideally driver class and other stubs should be written separately
public class Utility {

	private static WebDriver driver;

	public WebDriver setupForChrome() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		return driver;
	}

	public static void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	public static WebElement waitUntilExpectedConditions(ExpectedCondition<?> c, String errorMessageOnTimeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);

			return (WebElement) wait.until(c);

		} catch (Exception e) {
			System.out.println(errorMessageOnTimeout);
			// TODO: handle exception
			return null;
		}
	}

	public static String getValueFromJson(String key, String jsonFileName) {

		String value = null;
		Object obj;
		try {
			obj = new JSONParser().parse(new FileReader(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"clearTrip.data"+File.separator+jsonFileName + ".json"));
			JSONObject jo = (JSONObject) obj;
			value = (String) jo.get(key);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// typecasting obj to JSONObject

		// getting firstName and lastName

		return value;
	}

	public static String getTPlusNthDateInFormat(int n, String pattern) {
		LocalDate today = LocalDate.now();
		LocalDate todayAnd2 = today.plusDays(n);

		DateTimeFormatter d = DateTimeFormatter.ofPattern(pattern);
		String travelDate = todayAnd2.format(d);
		return travelDate;
	}

}
