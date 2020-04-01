package stravacustom.utils;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigHelper {

    private static PropertiesConfiguration config;

    public static String get(String key){

        if(config == null){
            try {
                config = new Configurations().properties( new File("config.properties"));
            } catch (ConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return config.getString(key);
    }
}
