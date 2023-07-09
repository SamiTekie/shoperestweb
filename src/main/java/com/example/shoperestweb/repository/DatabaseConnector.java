package com.example.shoperestweb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                String url = "jdbc:mysql://localhost:3306/shope";
                String username ="root";
                String password =null;


                connection = DriverManager.getConnection(url, username, password);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
