package data_provider;

import java.io.*;
import java.util.Properties;

import static java.util.Optional.ofNullable;

public class Configs {

    static {
        try {
            readProperties();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties properties;

    private static void readProperties() throws IOException {
        try {
            properties = new Properties();
            try (InputStream stream = Configs.class.getResourceAsStream("/configuration.properties")) {
                properties.load(stream);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("configuration.properties file is not found");
        }
    }

    public static String getBaseUrl() {
        return ofNullable(properties.getProperty("baseURL"))
                .orElseThrow(() -> new RuntimeException("baseURL not specified in the Configuration.properties file."));
    }

}
