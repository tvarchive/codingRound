package codingRoundBase;

import com.sun.javafx.PlatformUtil;

public class BaseClass   {
	 public static void setDriverPath() {
	        if (PlatformUtil.isMac()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver");
	        }
	        if (PlatformUtil.isWindows()) {
	        	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	        	         }
	        if (PlatformUtil.isLinux()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	        }

}}
