package codingRound.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Configurations {
	
	private static String fileSeparator = File.separator;
	
	private static final String Environment = "environment";
	private static final String Browser = "browser";
	private static final String Platform_Name = "platformname";
	private static final String OS = "os";
	private static final String TO_MAIL = "tomail";
	private static final String Results_Dir= "ResultsDir";
	private static final String Shared_Directory = "sharedDirectory";
	private static final String Resources ="resources";
	private static final String Resource_Dir = "resourceDir";
	
	Properties runtimeProperties;

	public Configurations() {

	}

	public Configurations(String browser, String environment, String platformName, String os, String sharedDirectory,
			String resultsdir, String tomail, ExtentTest logger) {
		try {
			// Read the config file
			this.runtimeProperties = new Properties();
			String path = System.getProperty("user.dir") + fileSeparator + "config.properties";
			FileInputStream fn = new FileInputStream(path);
			runtimeProperties.load(fn);
			fn.close();
			
			putRunTimeProperty("fileSeparator", fileSeparator);
			String resourceDir = System.getProperty("user.dir") + fileSeparator + getRunTimeProperty(Resources);
			putRunTimeProperty(Resource_Dir, resourceDir);
			
			overrideDefaultConfig(browser, environment, platformName, os, sharedDirectory,resultsdir, tomail);

		} catch (IOException e) {
			logger.log(LogStatus.FAIL, "Error while loading configurations", e);
		}

	}

	private void overrideDefaultConfig(String browser, String environment, String platformName, String os, String sharedDirectory,
	String resultsdir, String tomail) {
		// override the environment value if passed through mvn command line
					if (isNotBlank (environment))
						putRunTimeProperty(Environment, environment.toLowerCase());
					
					if (isNotBlank(browser))
						putRunTimeProperty(Browser, browser);

					if (isNotBlank(platformName))
						putRunTimeProperty(Platform_Name, platformName);

					if (isNotBlank(os))
						putRunTimeProperty(OS, os);

					if (isNotBlank(tomail))
						putRunTimeProperty(TO_MAIL, tomail);

					if (isNotBlank(resultsdir)) {
						putRunTimeProperty(Results_Dir, resultsdir);
					} else {
						String resultsDir = System.getProperty("user.dir") + fileSeparator + getRunTimeProperty(Results_Dir);
						putRunTimeProperty(Results_Dir, resultsDir);
					}
					if (isNotBlank(sharedDirectory))
						putRunTimeProperty(Shared_Directory, sharedDirectory);
					else {
						String sharedDir = System.getProperty("user.dir") + fileSeparator
								+ getRunTimeProperty(Shared_Directory);
						putRunTimeProperty(Shared_Directory, sharedDir);
					}
	}
	
	private boolean isNotBlank(String str) {
		return str != null && !str.equals("") && !str.equals(" ") && str.length()!=0;
	}

	public void putRunTimeProperty(String key, String value) {
		String keyName = key.toLowerCase();
		runtimeProperties.put(keyName, value);
	}

	public void removeRunTimeProperty(String key) {
		String keyName = key.toLowerCase();
		runtimeProperties.remove(keyName);
	}

	public String getRunTimeProperty(String key) {
		String keyName = key.toLowerCase();
		String value = "";
		try {
			value = runtimeProperties.get(keyName).toString();
		} catch (Exception e) {
			return null;
		}
		return value;
	}



}
