package codingRound.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



@Listeners({ SuiteListener.class })
public class Testbase {

	public static String path;
	public static String extentReportFile;
	public static ExtentReports extent;
	public static Configurations testConfig;
	public static String reqBrowser;
	public static ExtentTest logger;
	public WebDriver driver;


	@BeforeSuite(alwaysRun = true)
	@Parameters({ "browser", "environment", "platformName", "os", "sharedDirectory",
		"resultsDir", "tomail" })
	public void InitializeParameters(@Optional String browser, @Optional String environment,
			@Optional String platformName, @Optional String os, @Optional String sharedDirectory,
			@Optional String resultsdir,
			@Optional String tomail) throws IOException {

		testConfig = new Configurations(browser,environment,platformName,os,sharedDirectory,resultsdir,tomail,logger);
		reqBrowser = testConfig.getRunTimeProperty("browser");

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss_SSS");
		Date now = new Date();
		String strDate = sdfDate.format(now);

		path = testConfig.getRunTimeProperty("ResultsDir") + testConfig.getRunTimeProperty("fileSeparator") + strDate;

		new File(path).mkdirs();
		extentReportFile = path + testConfig.getRunTimeProperty("fileSeparator")
		+ testConfig.getRunTimeProperty("reportfilename");
		File file = new File(extentReportFile);
		file.createNewFile();
		extent = new ExtentReports(extentReportFile, true);
		logger = extent.startTest("Configurations for : " + getSuiteName());
		logger.log(LogStatus.INFO, "Details for starting the suite",
				"1. Browser name : " + testConfig.getRunTimeProperty("browser") + "</br>2. Environment : "
						+ testConfig.getRunTimeProperty("environment") + "</br>3. Platform Name : "
						+ testConfig.getRunTimeProperty("platformName") + "</br>4. OS : "
						+ testConfig.getRunTimeProperty("os") + "</br>5. To Mail : "
						+ testConfig.getRunTimeProperty("tomail"));
		extent.endTest(logger);
	}

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void startMethod(Method method) {
		Test test = method.getAnnotation(Test.class);
		if (test == null) {
			return;
		}
		String class_name = this.getClass().getName();
		logger = extent.startTest("Class_Name : " + class_name + "</br>" + "Test_Name : " + method.getName() + "</br>"
				+ "Test_Desc : " + test.description());
		logger.assignCategory(class_name);
		int flag = 1;
		try {

			if (testConfig.getRunTimeProperty("platformname").equalsIgnoreCase("desktop")) {
				if (reqBrowser.equalsIgnoreCase("notrequired")) {
					logger.log(LogStatus.INFO, "Browser required", "No");
					flag = 0;
				} else if (testConfig.getRunTimeProperty("os").equalsIgnoreCase("mac")) {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					System.setProperty("webdriver.chrome.driver", testConfig.getRunTimeProperty("SharedDirectory")
							+ testConfig.getRunTimeProperty("fileSeparator") + "chromedriver");

					driver = new ChromeDriver(options);
				} else if (testConfig.getRunTimeProperty("os").equalsIgnoreCase("windows")) {
					if (reqBrowser.equalsIgnoreCase("chrome")) {
						System.setProperty("webdriver.chrome.driver", testConfig.getRunTimeProperty("SharedDirectory")
								+ testConfig.getRunTimeProperty("fileSeparator") + "chromedriver_windows.exe");
						driver = new ChromeDriver();
					} else if (reqBrowser.equalsIgnoreCase("mozilla")) {
						System.setProperty("webdriver.gecko.driver", testConfig.getRunTimeProperty("SharedDirectory")
								+ testConfig.getRunTimeProperty("fileSeparator") + "geckodriver.exe");
						FirefoxProfile profile = new FirefoxProfile();
						profile.setPreference("network.proxy.type", ProxyType.SYSTEM.ordinal());
						driver = new FirefoxDriver();
					}
				}
			}


		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Browser Open", e.getLocalizedMessage());
			extent.endTest(logger);
		}

		if (flag == 1) {
			logger.log(LogStatus.INFO, "Browser Open", "Browser opened successfully");
			driver.manage().window().maximize();
			logger.log(LogStatus.INFO, "Browser Maximize", "Browser maximized sucessfully");
		}
	}

	@AfterMethod(alwaysRun = true)
	public void endMethod(ITestResult result) throws IOException {
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				logger.log(LogStatus.FAIL, result.getName() + " : Test case failed due to : ", result.getThrowable());

				if (!reqBrowser.equalsIgnoreCase("notrequired")) {
					File scrf_a = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

					String encodedBase64 = null;
					FileInputStream fileInputStreamReader = null;
					try {
						fileInputStreamReader = new FileInputStream(scrf_a);
						byte[] bytes = new byte[(int) scrf_a.length()];
						fileInputStreamReader.read(bytes);
						encodedBase64 = new String(Base64.encodeBase64(bytes));

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String final_file_path = "data:image/png;base64," + encodedBase64;
					String image_a = logger.addScreenCapture(final_file_path);
					logger.log(LogStatus.FAIL, "Test case failed. Please check - visible image", image_a);


				}
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, result.getName() + " : Test case passed");
			} else {
				logger.log(LogStatus.SKIP, result.getName() + " : Test case skipped due to : ", result.getThrowable());
			}

		} catch (Exception e) {
			logger.log(LogStatus.WARNING, "Final test step failed due to : ", e.toString());
		} finally {
			if ((!reqBrowser.equalsIgnoreCase("notrequired"))
					&& (testConfig.getRunTimeProperty("platformname").equalsIgnoreCase("desktop"))) {
				driver.quit();
				logger.log(LogStatus.INFO, "Browser Closed");
			} 
			extent.endTest(logger);
		}
	}

	@AfterSuite(alwaysRun = true)
	public void dumpParameters() throws IOException {
		extent.flush();
		extent.close();
		String bodyData = "1. Browser name : " + testConfig.getRunTimeProperty("browser") + "\n2. Environment : "
				+ testConfig.getRunTimeProperty("environment") + "\n3. Platform Name : "
				+ testConfig.getRunTimeProperty("platformName") + "\n4. OS : " + testConfig.getRunTimeProperty("os")
				+ "\n5. To Mail : " + testConfig.getRunTimeProperty("tomail") + "\n\n PFA report.";

		//code to send mail to given mail id(s)
		/*sendmail sendmail = new sendmail();
			sendmail.SendMail(testConfig.getRunTimeProperty("tomail"), testConfig.getRunTimeProperty("replyto"),
					"Automation Report : " + getSuiteName(), path, testConfig.getRunTimeProperty("reportemail"),
					testConfig.getRunTimeProperty("reportpassword"), bodyData);*/
	}

	public String getSuiteName() {
		ISuite suiteListner = SuiteListener.getAccess();
		String runningSuite = suiteListner.getName();
		return runningSuite;
	}



}
