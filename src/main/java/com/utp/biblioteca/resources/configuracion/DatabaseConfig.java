package com.utp.biblioteca.resources.configuracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private Properties properties;

    public DatabaseConfig() {
        properties = new Properties();

        try ( FileInputStream fis = new FileInputStream("src/main/resources/db/db.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return properties.getProperty("db.url");
    }

    public String getUsername() {
        return properties.getProperty("db.username");
    }

    public String getPassword() {
        return properties.getProperty("db.password");
    }
}