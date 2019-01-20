package com.testvagrant.automation.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeUtils {

    private static Logger logger = Logger.getLogger(ChromeUtils.class);

    public static void setWindowsSize(ChromeOptions options, WindowSize windowSize) {
        options.addArguments(windowSize.getDefinition());
        logger.info(windowSize.getDefinition());
    }

    public static void addDisableNotifications(ChromeOptions options) {
        options.addArguments("--disable-notifications");
    }

    public static void addDisableInfoBars(ChromeOptions options) {
        options.addArguments("--disable-infobars");
    }


    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        ChromeUtils.setWindowsSize(options, WindowSize.getWindowSize());
        ChromeUtils.addDisableNotifications(options);
        ChromeUtils.addDisableInfoBars(options);
        return options;
    }
}

