package supportlibraries;

import org.openqa.selenium.WebDriver;

public abstract class ReusableLibrary {

	protected WebDriver driver;

	protected ScriptHelper scriptHelper;

	public ReusableLibrary(ScriptHelper scriptHelper) {
		this.scriptHelper = scriptHelper;
		this.driver = scriptHelper.getDriver();

	}

}
