package com.higgsup.intern.jdbc_demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/higgsup_intern_training";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}
