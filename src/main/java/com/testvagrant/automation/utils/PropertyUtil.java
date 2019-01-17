package com.testvagrant.automation.utils;


import javafx.util.Pair;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * created by sahil.kashyap on 17/01/19
 */
public class PropertyUtil {

    public static Properties properties;
    public static String DynamicElementValue = "";
    public static String propertyFileName;

    public static void loadPropertyFile(String mapFile) {
        properties = new Properties();
        propertyFileName = mapFile;
        try {
            FileInputStream Master = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + mapFile);
            properties.load(Master);
            Master.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Pair<String, String> getLocator(Enum element) {
        String type;
        String path;
        try {
            String value = properties.getProperty(element.name().toUpperCase());
            String[] values = value.split("_", 2);
            if (values.length == 2) {
                type = values[0].toLowerCase();
                path = values[1];
            }else{
                throw new RuntimeException(String.format("%s value of %s located in %s is bad formatted."
                        , value, element.name(), propertyFileName));
            }
        } catch (NullPointerException e) {
            throw new NoSuchElementException(
                    String.format("'%s' is not defined in %s", element.name(), propertyFileName)
            );
        }
        return new Pair(type, path);
    }

    public static By getLocator(String ElementName) {
        String ElementNameValue;
        String locatorType = null;
        String locatorValue;
        try {
            ElementNameValue = properties.getProperty(ElementName);
            String[] values = ElementNameValue.split("_");
            locatorType = values[0];
            locatorValue = values[1];
            if (values.length > 2) {
                locatorValue = "";
                locatorType = values[0];
                for (int i = 1; i < values.length; i++) {
                    locatorValue += values[i];
                }
            }
        } catch (NullPointerException e) {
            throw new NoSuchElementException(ElementName + " is not defined in " + propertyFileName);
        } catch (Exception e) {
            throw new NoSuchElementException("Locator type '" + locatorType + "' not defined in the prop file!!");

        }
        if (locatorValue.contains("%s")) {
            locatorValue = String.format(locatorValue, DynamicElementValue);
        }
        if (locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if (locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.tagName(locatorValue);
        else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if (locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if (locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else if (locatorType.toLowerCase().equals("automation"))
            return By.xpath(String.format("//*[@data-aut-id='%s']", locatorValue));
        else
            throw new NoSuchElementException("Locator type '" + locatorType + "' not defined in the prop file!!");
    }

    public static String getPropertyValue(String key) {
        if (properties.getProperty(key) == null) {
            throw new NoSuchFieldError("Property key not found in properties file:" + key);
        }
        return properties.getProperty(key);

    }

}
