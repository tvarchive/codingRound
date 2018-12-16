package com.coding.uicontroller;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class Loggers {

	public static Logger log = LogManager.getLogger(Loggers.class.getName());

	public static void setLogger() {

		/*
		 * get the Verbose value from System environment variable to get the
		 * logLevel. if Verbose=1 then logLevel is set to DEBUG and else by
		 * default logLevel set to INFO Level for logs according to the priority
		 * (ALL > TRACE > DEBUG > INFO > WARN> ERROR > FATAL > OFF )
		 */
		// log.getRootLogger().setLevel(Level.OFF);
		try {
			final String value = System.getenv("evgm.log.Verbose");
			if (value.equals("1"))
				log.setLevel(Level.DEBUG);
			else
				log.setLevel(Level.INFO);
		} catch (Exception e) {
			log.setLevel(Level.INFO);
		}

		/*
		 * PatternLayout represents the pattern of logs that is generated.
		 * %d:date, %t:Thread Name, %p:levels(info,warn,error e.t.c), m:mesages,
		 * n:new line
		 */

		PatternLayout layout = new PatternLayout("[%d{dd MMM yyyy HH:mm:ss}] [%t] [%p] %m%n");

		// Add console appender to logger to output logs in Console
		log.addAppender(new ConsoleAppender(layout));

		try {
			File directory = null;

			// To get current path of directory
			final String cwd = System.getProperty("user.dir");

			directory = new File(cwd + File.separator + "Logs");
			if (!directory.exists()) {
				directory.mkdirs();
			}
			// Define file appender with layout and output log file name
			RollingFileAppender fileAppender = new RollingFileAppender(layout, directory + File.separator + "Logs.log");

			// Add the appender to logger to output logs in File
			log.addAppender(fileAppender);
		} catch (IOException e) {
			log.error("Failed to add appender !!");
		}
	}

	/***
	 * info():logs the information which is helpful in track the application
	 * flow
	 * 
	 * @param msg:message
	 *            is passed as parameter which is to be logged
	 */
	public static void info(String msg) {
		log.info(String.format("[%s]", Thread.currentThread().getId()) + " " + Thread.currentThread().getStackTrace()[2]
				+ " - " + msg);
	}

	/***
	 * warn():Designates potentially harmful situations.
	 * 
	 * @param msg:message
	 *            is passed as parameter which is to be logged
	 */
	public static void warn(String msg) {
		log.warn(String.format("[%s]", Thread.currentThread().getId()) + " " + Thread.currentThread().getStackTrace()[2]
				+ " - " + msg);
	}

	/***
	 * error():Designates error events that might still allow the application to
	 * continue running. Any exceptions under catch is handled using error
	 * method.
	 * 
	 * @param msg:message
	 *            is passed which is to be logged
	 */
	public static void error(String msg) {
		log.error(String.format("[%s]", Thread.currentThread().getId()) + " "
				+ Thread.currentThread().getStackTrace()[2] + " - " + msg);
	}

	/***
	 * fatal():Designates very severe error events that will presumably lead the
	 * application to abort.
	 * 
	 * @param msg:message
	 *            is passed which is to be logged
	 */
	public static void fatal(String msg) {
		log.fatal(String.format("[%s]", Thread.currentThread().getId()) + " "
				+ Thread.currentThread().getStackTrace()[2] + " - " + msg);
	}

	/***
	 * trace():Designates finer-grained informational events than the DEBUG.
	 * 
	 * @param msg:message
	 *            is passed which is to be logged
	 */
	public static void trace(String msg) {
		log.trace(String.format("[%s]", Thread.currentThread().getId()) + " "
				+ Thread.currentThread().getStackTrace()[2] + " - " + msg);
	}

	/***
	 * debug():Designates fine-grained informational events that are most useful
	 * to debug an application. debug method takes time to evaluate, in that
	 * case you can skip this evaluation if debug is not enabled.
	 * 
	 * @param msg:message
	 *            is passed which is to be logged
	 */
	public static void debug(String msg) {
		if (log.isDebugEnabled()) {
			log.debug(String.format("[%s]", Thread.currentThread().getId()) + " "
					+ Thread.currentThread().getStackTrace()[2] + " - " + msg);
		}
	}
}