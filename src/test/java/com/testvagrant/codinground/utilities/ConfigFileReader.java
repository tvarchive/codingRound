package com.testvagrant.codinground.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {
	
	private String defaultConfigFile = System.getProperty("user.dir")+ File.separator+ "Resources" + File.separator+"Configurations.properties";

	   
    public String getProperty(String property) {
    	
    	
    	Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(defaultConfigFile));
            if (inputStream != null) {
                properties = new Properties();
                properties.load(inputStream);
                
                return properties.getProperty(property);
            }
        }catch(Exception ex){
        	System.out.println("Problems in fetching values from config file");
        }
        return null;
    }

	
}
