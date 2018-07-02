package com.higgsup.intern.w02.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.manager.StudentManeger;
import com.higgsup.intern.w02.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentServlet extends HttpServlet {
    private StudentManeger mnStu;
    ObjectMapper obmStu = new ObjectMapper();

    public StudentServlet() {
        super();
        mnStu = new StudentManeger();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        PrintWriter out = response.getWriter();

        Student student = obmStu.readValue(json,Student.class);

        try {
            boolean code = mnStu.insertStudent(student);
           out.println(obmStu.writeValueAsString(code));
            System.out.println("Insert successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        BufferedReader reader = request.getReader();
        int id = Integer.parseInt(request.getParameter("id"));
        String json = reader.readLine();
        PrintWriter out = response.getWriter();

        Student student = obmStu.readValue(json,Student.class);

        try {
            boolean code = mnStu.updateStudent(student,id);
            out.println(obmStu.writeValueAsString(code));
            System.out.println("Update successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        try {
            boolean code = mnStu.deleteStudent(id);
            out.println(obmStu.writeValueAsString(code));
            System.out.println("Delete successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            out.println(obmStu.writeValueAsString(mnStu.displayStudent()));
            System.out.println("Display successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
