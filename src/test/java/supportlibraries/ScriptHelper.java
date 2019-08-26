package supportlibraries;

import org.openqa.selenium.WebDriver;

public class ScriptHelper {
	private final WebDriver driver;

	public ScriptHelper(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

}
