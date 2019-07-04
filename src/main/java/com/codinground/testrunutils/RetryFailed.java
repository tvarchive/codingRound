package com.codinground.testrunutils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.codinground.ioutils.PropertiesFileUtil;

public class RetryFailed implements IRetryAnalyzer{

	private PropertiesFileUtil objProperty = new PropertiesFileUtil();
	private int counter =0;
	private String retriesLimiter = objProperty.readProperty("numberOfRetriesFailed");
	
	public boolean retry(ITestResult result) {
		//&&(!result.isSuccess())
		
		while(counter<Integer.valueOf(retriesLimiter)) {
			counter++;
			return true;
		}
		return false;
	}

}
