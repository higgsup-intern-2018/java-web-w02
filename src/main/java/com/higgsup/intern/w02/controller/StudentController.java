package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.StudentDAO;
import com.higgsup.intern.w02.model.Response;
import com.higgsup.intern.w02.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentController extends HttpServlet {
    private StudentDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();

    public StudentController() {
        dao = new StudentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.println(objectMapper.writeValueAsString(dao.getStudentList()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        try {
            Response res = new Response();
            res.setSuccess(dao.deleteById(id));
            out.println(objectMapper.writeValueAsString(res));
            System.out.println("Delete successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        BufferedReader reader = request.getReader();
        String json = reader.readLine();

        Student student = objectMapper.readValue(json, Student.class);

        try {
            Response res = new Response();
            res.setSuccess(dao.insert(student));
            out.println(objectMapper.writeValueAsString(res));
            System.out.println("Insert successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));

        BufferedReader reader = request.getReader();
        String json = reader.readLine();

        Student student = objectMapper.readValue(json, Student.class);

        try {
            Response res = new Response();
            res.setSuccess(dao.update(student, id));
            out.println(objectMapper.writeValueAsString(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
