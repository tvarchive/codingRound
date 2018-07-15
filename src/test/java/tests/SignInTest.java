package tests;
import helpers.DriverInit;
import helpers.ElementFinder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

	@Test(alwaysRun=true, testName="SignIn-ErrorVaidation")
	public void testCaseInitialization() {
		helpers.Log.startTestCase("TC_01" + ":" + "SignIn-ErrorVaidation");
		DriverInit.setDriver();
		DriverInit.driverInit("https://www.cleartrip.com/");
		DriverInit.getDriver().findElement(ElementFinder.getByType("linktext", "Your trips")).click();
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "SignIn")).click();
		DriverInit.getDriver().switchTo().frame("modal_window");
		DriverInit.getDriver().findElement(ElementFinder.getByType("id", "signInButton")).click();
		String errors1 = DriverInit.getDriver().findElement(ElementFinder.getByType("id", "errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		DriverInit.driverClose();
		helpers.Log.endTestCase("SignInTest");
	}
}
