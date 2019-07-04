package com.codinground.reportutils;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.codinground.driverutiils.DriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ReportListener implements ITestListener {
	
	
	 
	 protected static ExtentReports reports;
	 protected static ExtentTest test;
	 private ITestContext context;
	 private DriverFactory objDriverFac = new DriverFactory();

	 public void onTestStart(ITestResult result) {
		  System.out.println("on test start");
		  test = reports.startTest(result.getMethod().getMethodName());
		  test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
		 }
	 
	  public void onTestSuccess(ITestResult result) {
	  System.out.println("on test success");
	  test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	  TakesScreenshot ts = (TakesScreenshot) objDriverFac.getDriver();
	  File src = ts.getScreenshotAs(OutputType.FILE);
	   try {
		   FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\ExtentReports\\"+context.getName()+"_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_passed.png"));
		  // String file = test.addScreenCapture("./" + result.getTestName()+result.getMethod().getMethodName() + ".png");
		   String file = test.addScreenCapture(System.getProperty("user.dir") + "\\ExtentReports\\"+context.getName()+"_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_passed.png");
		   System.out.println("**********************************=======================================************************************************");
		   test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test passed", file);
		   test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test passed");
		  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }
	  
	  
	  
	 public void onTestFailure(ITestResult result) {
	  System.out.println("on test failure");
	  test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");
	  TakesScreenshot ts = (TakesScreenshot) objDriverFac.getDriver();
	  File src = ts.getScreenshotAs(OutputType.FILE);
	    try {
		   FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "\\ExtentReports\\"+context.getName()+"_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_failed.png"));
		   String file = test.addScreenCapture(System.getProperty("user.dir") + "\\ExtentReports\\"+context.getName()+"_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_failed.png");
		   System.out.println("**********************************=======================================************************************************");
		   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test failed", file);
		   test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test failed", result.getThrowable().getMessage());
		  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }
	 
	 
	 public void onTestSkipped(ITestResult result) {
	  System.out.println("on test skipped");
	  test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	 }
	 
	 
	 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	  System.out.println("on test sucess within percentage");
	 }
	 
	 
	 public void onStart(ITestContext context) {
		  System.out.println("on start");
		  String workingDir = System.getProperty("user.dir");
		  this.context = context;
		 reports = new ExtentReports(workingDir + "\\ExtentReports\\"+context.getName()+"_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_ExtentReportResults.html",true);
		  
	 }
	 
	 
	 public void onFinish(ITestContext context) {
		  System.out.println("on finish");
		  reports.endTest(test);
		  reports.flush();
		 }
}

