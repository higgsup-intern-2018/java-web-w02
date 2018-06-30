package com.higgsup.intern.w02.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.manager.StudentManeger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentServlet extends HttpServlet {
    private StudentManeger mn;
    ObjectMapper obm = new ObjectMapper();

    public StudentServlet() {
        super();
        mn = new StudentManeger();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.println(obm.writeValueAsString(mn.displayStudent()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
