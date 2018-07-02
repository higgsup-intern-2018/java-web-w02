package com.higgsup.intern.w02.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.manager.StudentInfoManager;
import com.higgsup.intern.w02.manager.StudentManeger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentInfoServlet extends HttpServlet {
    private StudentInfoManager mn;
    ObjectMapper obm = new ObjectMapper();

    public StudentInfoServlet() {
        super();
        mn = new StudentInfoManager();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            out.println(obm.writeValueAsString(mn.displayStudentInfo(id)));
            System.out.println("Display successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
