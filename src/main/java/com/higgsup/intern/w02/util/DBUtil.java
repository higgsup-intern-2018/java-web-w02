package com.higgsup.intern.w02.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String USERNAME = "thuyngo";
    public static final String PASS = "1234$";
    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/higgsup_intern_tranning";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASS);
    }
}
