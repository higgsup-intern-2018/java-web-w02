package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.StudentInfoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentInfoController extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    private StudentInfoDAO dao;

    public StudentInfoController() {
        super();
        dao = new StudentInfoDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            out.println(objectMapper.writeValueAsString(dao.displayStudentInfo(id)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
