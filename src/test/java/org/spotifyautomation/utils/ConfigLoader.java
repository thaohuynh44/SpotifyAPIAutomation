package org.spotifyautomation.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getProperty(String propertyName) {
        String prop = properties.getProperty(propertyName);
        if(prop != null) return prop;
        else throw new RuntimeException("Property " + propertyName + " is not specified in the config.properties file");
    }
}
