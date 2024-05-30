package com.utp.biblioteca.resources.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conn;
    DatabaseConfig databaseConfig = new DatabaseConfig();

    public Connection getConectar() throws SQLException {
        String url = databaseConfig.getUrl();
        String username = databaseConfig.getUsername();
        String password = databaseConfig.getPassword();

        conn = DriverManager.getConnection(url, username, password);

        return conn;
    }
}