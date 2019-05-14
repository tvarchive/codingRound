package com.testvagrant.utils;

public class CustomLogger {

	/**
	 * Logs message to console
	 * 
	 * @param message : message text to be logged to the console
	 */
	public void log(String message) {
		// Could be replaced with Log4j for real time project purposes
		System.out.println("\nLog: " + message);
	}

	/**
	 * Loggs exception stacktrace to console
	 * 
	 * @param e : Exception
	 */
	public void logException(Exception e) {
		System.out.println("\nError Log: " + e.getStackTrace());
	}
}
