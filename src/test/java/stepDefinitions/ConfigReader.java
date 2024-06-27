package stepDefinitions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            //properties.load(new FileInputStream("config.properties"));
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key) {
        String value = properties.getProperty(key, "");
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value for key " + key + " is null or empty in the config.properties file.");
        }
        //return properties.getProperty(key);
        return value;
    }
}
