package com.higgsup.intern.w02.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentConnection {
    private static final String USERNAME ="root";
    private static final String PASSWORD ="123456";
    private static final String CONNECTION_URL ="jdbc:mysql://localhost:3306/higgsup_intern_trainin";

    public static Connection getConnection() throws SQLException{
     try{
        Class.forName("com.mysql.cj.jdbc.Driver");
     }catch (ClassNotFoundException e){
         System.err.println("Can not load MySQL Driver");
     }
     return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}
