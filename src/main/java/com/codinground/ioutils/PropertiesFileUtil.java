package com.codinground.ioutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileUtil {

	 Properties properties = new Properties();
	    InputStream inputStream = null;

	    public PropertiesFileUtil() {
	        loadProperties();
	    }

	    private void loadProperties() {
	        try {
	            inputStream = new FileInputStream("./src/test/resources/testconfig/uitestconfig.properties");
	            properties.load(inputStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public String readProperty(String key) {
	        return properties.getProperty(key);
	    }
}
