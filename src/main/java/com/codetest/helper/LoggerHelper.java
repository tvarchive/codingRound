package com.codetest.helper;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;


public class LoggerHelper
{
	private static PatternLayout layout = null;
	private static HTMLLayout layouthtml = null;
	private static ConsoleAppender console = null;
	private static RollingFileAppender rolling = null;
	private static FileAppender file = null;
	private static Logger rootlogger = null;
	private static Logger logger = null;
	private static final String pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} : %L -[%M] %m%n";
	private static boolean flag = false;
	
	private static void initLogger()
	{
		layout = new PatternLayout(pattern);
		layouthtml = new HTMLLayout();
		/* Console Appender */
		
		console = new ConsoleAppender();
		console.setName("STDOUT");
		console.setTarget("System.out");
		console.setLayout(layout);
		console.setThreshold(Level.INFO);
		console.activateOptions();

		/* Rolling file Appender */

		try {
			rolling = new RollingFileAppender(layout, "log/rollingfile.log");
			rolling.setName("RFILE");
			rolling.setThreshold(Level.INFO);
			rolling.setMaxFileSize("5MB");
			rolling.setMaxBackupIndex(100);
			rolling.activateOptions();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* File Appender */
		
		try {
			file = new FileAppender(layouthtml, "html/logs_html.html");
			file.setName("FILE");
			file.setThreshold(Level.DEBUG);
			file.activateOptions();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		/*Root Logger */
		
		rootlogger = Logger.getRootLogger();
		rootlogger.addAppender(console);
		rootlogger.addAppender(rolling);
		rootlogger.addAppender(file);
		

	}
	
	public static Logger getLogger(Class aClass)
	{
		if(!flag)
		{
			initLogger();
		flag=true;
		LoggerHelper.logger = Logger.getLogger(aClass);
		return LoggerHelper.logger;
		}else 			
		{
			LoggerHelper.logger = Logger.getLogger(aClass);
			return LoggerHelper.logger;
			
		}
			
	}

}

