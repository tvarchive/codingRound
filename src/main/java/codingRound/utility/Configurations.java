package codingRound.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Configurations {
	
	public static String BrowserName;
	public static String Environment;
	public static String PlatformName;
	public static String os;
	public static String SharedDirectory;
	public static String ResultsDir;
	public static String fileSeparator = File.separator;
	public static String tomail;
	Properties runtimeProperties;

	public Configurations() {

	}

	public Configurations(ExtentTest logger) {
		try {
			// Read the config file
			Properties property = new Properties();
			String path = System.getProperty("user.dir") + fileSeparator + "config.properties";
			FileInputStream fn = new FileInputStream(path);
			property.load(fn);
			fn.close();

			this.runtimeProperties = new Properties();
			Enumeration<Object> em = property.keys();
			while (em.hasMoreElements()) {
				String str = (String) em.nextElement();
				putRunTimeProperty(str, (String) property.get(str));
			}

			// override the environment value if passed through mvn command line
			if (!(Environment == null || Environment.isEmpty()))
				putRunTimeProperty("Environment", Environment.toLowerCase());

			if (!(BrowserName == null || BrowserName.isEmpty()))
				putRunTimeProperty("Browser", BrowserName);

			if (!(PlatformName == null || PlatformName.isEmpty()))
				putRunTimeProperty("PlatformName", PlatformName);

			if (!(os == null || os.isEmpty()))
				putRunTimeProperty("os", os);

			if (!(tomail == null || tomail.isEmpty()))
				putRunTimeProperty("tomail", tomail);

			if (!(ResultsDir == null || ResultsDir.isEmpty())) {
				putRunTimeProperty("ResultsDir", ResultsDir);
			} else {
				String resultsDir = System.getProperty("user.dir") + fileSeparator + getRunTimeProperty("ResultsDir");
				putRunTimeProperty("ResultsDir", resultsDir);
			}
			if (!(SharedDirectory == null || SharedDirectory.isEmpty()))
				putRunTimeProperty("SharedDirectory", SharedDirectory);
			else {
				String sharedDir = System.getProperty("user.dir") + fileSeparator
						+ getRunTimeProperty("shareddirectory");
				putRunTimeProperty("SharedDirectory", sharedDir);
			}

			putRunTimeProperty("fileSeparator", fileSeparator);
			String resourceDir = System.getProperty("user.dir") + fileSeparator + getRunTimeProperty("Resources");
			putRunTimeProperty("ResourceDir", resourceDir);

		} catch (IOException e) {
			logger.log(LogStatus.FAIL, "Error while loading configurations", e);
		}

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
