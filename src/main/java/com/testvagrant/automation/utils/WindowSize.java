package com.testvagrant.automation.utils;

import org.apache.log4j.Logger;

public enum WindowSize {

    DESKTOP_LARGE("1280,1024"),
    MOBILE("400,800"),
    PHABLET("485,800"),
    TABLET("770,1024"),
    DESKTOP("1090,1024");

    private String definition;

    WindowSize(String size) {
        definition = "window-size=" + size;
    }

    public String getDefinition(){
        return definition;
    }

    public boolean isMobile() {
        return WindowSize.MOBILE.name().equals(this.name());
    }

    public boolean isDesktop() {
        return WindowSize.DESKTOP.name().equals(this.name());
    }

    public boolean isDesktopLarge() {
        return WindowSize.DESKTOP_LARGE.name().equals(this.name());
    }

    public static WindowSize getWindowSize() {
        try {
            return valueOf(System.getProperty("windowSize").toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ignored) {
            Logger.getLogger(WindowSize.class)
                    .warn("The window size is not valid or was not provided: Continue with DESKTOP.");
            return WindowSize.DESKTOP_LARGE;
        }
    }

}
