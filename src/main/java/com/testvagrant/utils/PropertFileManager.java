package com.testvagrant.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class PropertFileManager {
	private static final Map<String, Properties> propertyObjectPool = new LinkedHashMap<>();
	private static CustomLogger logger = new CustomLogger();

	/**
	 * Getting value from properties file
	 * 
	 * @param propertyName     : Name of the property
	 * @param propertyFileName : Name of the properties file
	 * @return : Property value
	 */
	public static String getPropertyFromFile(String propertyName, String propertyFileName) {
		return loadProperties(propertyFileName).getProperty(propertyName);

	}

	/**
	 * Loading file values to the properties object if it wasn't loaded earlier
	 * 
	 * @param propertyFileName : Name of property file
	 * @return : Properties object with loaded values
	 */
	private static Properties loadProperties(String propertyFileName) {
		Properties prop;
		if (propertyObjectPool.containsKey(propertyFileName)) {
			prop = propertyObjectPool.get(propertyFileName);
		} else {
			prop = new Properties();
			String path = System.getProperty("user.dir");
			File propFile = null;

			// Used when there are more number of properties files
			switch (propertyFileName) {
			case MiscConstants.PROP_OR:
				propFile = new File(path + "\\FileUtils\\ObjectRepository.properties");
				break;
			case MiscConstants.PROP_MISC:
				propFile = new File(path + "\\FileUtils\\MiscValues.properties");
				break;

			default:
				propFile = new File(path + "\\FileUtils\\ObjectRepository.properties");
				break;
			}
			try {
				prop.load(new FileInputStream(propFile));
			} catch (Exception e) {
				logger.logException(e);
			}
			propertyObjectPool.put(propertyFileName, prop);
		}
		return prop;
	}
}
