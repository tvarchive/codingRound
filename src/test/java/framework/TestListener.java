package test.java.framework;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * TestListener.java
 * 
 * Implements TestNG Listeners to add custom logs
 * for every TestNG phase (BeforeSuite, BeforeClass, Test etc.)
 * 
 * Few Listeners have been commented assuming not required as of now
 * 
 * Need to be linked to every Test file to enable Listeners
 *
 */
public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	// This belongs to ISuiteListener and will execute before the Suite start

	@Override

	public void onStart(ISuite arg0) {
		Reporter.log("[INFO] About to begin executing Suite " + arg0.getName(), true);
	}

	// This belongs to ISuiteListener and will execute, once the Suite is finished

	@Override

	public void onFinish(ISuite arg0) {
		Reporter.log("[INFO] About to end executing Suite " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute before starting of Test
	// set/batch

	public void onStart(ITestContext arg0) {
//		Reporter.log("[INFO] About to begin executing Test " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute, once the Test set/batch is
	// finished

	public void onFinish(ITestContext arg0) {
//		Reporter.log("[INFO] Completed executing test " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute only when the test is pass

	public void onTestSuccess(ITestResult arg0) {
		// printTestResults(arg0);
	}

	// This belongs to ITestListener and will execute only on the event of fail test
	public void onTestFailure(ITestResult arg0) {
		Reporter.log("[ERROR] Test Failed!");
		// take screenshot on failure
		String methodName = arg0.getName();
		try {
			String currentTime = getTime();

			BaseTest currentClass = (BaseTest) arg0.getInstance();
			WebDriver wd = ((BaseTest) currentClass).driver;
			String scrFileName = "";
			scrFileName = "screenshots/" + arg0.getName() + "_" + currentTime + ".png";

			File scrFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File(scrFileName));
			Reporter.log("[" + methodName + "]: Taking screenshot on failure: " + scrFileName, true);
		} catch (Exception e) {
			System.err.println("Error taking screenshot... Continuing");
			e.printStackTrace();
		}

		// This is calling the printTestResults method
		printTestResults(arg0);

	}

	// This belongs to ITestListener and will execute before the main test start
	// (@Test)

	public void onTestStart(ITestResult arg0) {
//		Reporter.log("[INFO] The execution of the main test starts now " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute only if any of the main
	// test(@Test) get skipped

	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test

	private void printTestResults(ITestResult result) {

		// Reporter.log("Test Method resides in " + result.getTestClass().getName(),
		// true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("[INFO] Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("[INFO] Test Status: " + status, true);

	}

	// This belongs to IInvokedMethodListener and will execute before every method
	// including @Before @After @Test

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// String textMsg = "About to begin executing following method : " +
		// returnMethodName(arg0.getTestMethod());
		//
		// Reporter.log(textMsg, true);

	}

	// This belongs to IInvokedMethodListener and will execute after every method
	// including @Before @After @Test

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// String textMsg = "Completed executing following method : " +
		// returnMethodName(arg0.getTestMethod());
		//
		// Reporter.log(textMsg, true);

	}

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

	private final static String getTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		df.setTimeZone(TimeZone.getTimeZone("IST"));

		return (df.format(new Date()));
	}

}