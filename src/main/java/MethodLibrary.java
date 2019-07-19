import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

// one single method library as we have very few methods as of now
//ideally driver class and other stubs should be written separately
public class MethodLibrary {

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
}
