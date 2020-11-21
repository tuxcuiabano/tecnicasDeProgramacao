package com.example.config;

import java.io.IOException;
import java.util.Properties;

public class ApiConfig {

    private String key;
    private String secret;
    private String url;

    public ApiConfig() throws IOException {
        this.loadProperties();
    }

    private void loadProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("api.properties"));

        this.key = prop.getProperty("KEY");
        this.secret = prop.getProperty("SECRET");
        this.url = prop.getProperty("URL");
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public String getUrl() {
        return url;
    }
}
