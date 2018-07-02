package com.higgsup.intern.w02.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kangmina";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/higgsup_intern_training";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Can not load MySQL Driver.");
        }
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}
