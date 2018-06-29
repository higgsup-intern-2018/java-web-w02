package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.StudentDAO;
import com.higgsup.intern.w02.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentController extends HttpServlet {
    private StudentDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();


    public StudentController() {
        super();
        dao = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.println(objectMapper.writeValueAsString(dao.displayAll()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
