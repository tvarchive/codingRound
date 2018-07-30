package test.java.framework;

import java.util.HashMap;

import org.testng.Reporter;

/**
 * 
 * TestConfig.java
 * 
 * Reads test data configuration based on provided config data source
 * 
 * Current support: coomand line TBD: XML | Web Page
 *
 */
public class TestConfig {

	/**
	 * method getConfigData
	 * Reads test config data for given source
	 * 
	 * @return HashMap
	 */
	public HashMap<String, String> getConfigData() {
		HashMap<String, String> configData = new HashMap<String, String>();

		String configSource = System.getProperty("configSource");

		if (configSource != null) {
			switch (configSource) {
			case "cmd": {

				String url = System.getProperty("appURL");
				configData.put("appUrl", url);

				String browser = System.getProperty("browser");
				configData.put("browser", browser);

				String host = System.getProperty("host");

				if (host != null) {
					if (host.equalsIgnoreCase("saucelabs")) {

						String browserVersion = System.getProperty("browserVersion");
						String os = System.getProperty("os");
						String buildname = System.getProperty("buildname");

						configData.put("host", "saucelabs");
						configData.put("browserVersion", browserVersion);
						configData.put("os", os);
						configData.put("sauceUsername", FWConstants.SAUCE_USERNAME);
						configData.put("sauceAccesskey", FWConstants.SAUCE_ACCESKEY);
						configData.put("buildname", buildname);

						return configData;

					} else if (host.equalsIgnoreCase("local")) {
						configData.put("host", "local");
						return configData;
					} else {
						Reporter.log("[ERROR] invalid host...returning NULL as driver !! " + host, true);
						return null;
					}
				} else {
					configData.put("host", "local");
					return configData;

				}
			}
			default: {
				return null;
			}
			}
		}

		else {
			configData.put("host", "local");

			return configData;
		}
	}

}
