package com.eksamen.projectcalculator.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection != null) return connection;
            url = System.getenv("url");
            user = System.getenv("user");
            password = System.getenv("password");
        try {
            connection = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
