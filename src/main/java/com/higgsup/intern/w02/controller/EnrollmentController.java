package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.EnrollmentDAO;
import com.higgsup.intern.w02.model.Enrollment;
import com.higgsup.intern.w02.model.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class EnrollmentController extends HttpServlet {
    private EnrollmentDAO dao;
    ObjectMapper objectMapper = new ObjectMapper();

    public EnrollmentController() {
        super();
        dao = new EnrollmentDAO();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int classroomId = Integer.parseInt(request.getParameter("classroomId"));
        PrintWriter out = response.getWriter();

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setClassroomId(classroomId);

        try {
            Response res = new Response();
            res.setSuccess(dao.insert(enrollment));
            out.println(objectMapper.writeValueAsString(res));
            System.out.println("Insert successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classroomId = Integer.parseInt(request.getParameter("classroomId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        PrintWriter out = response.getWriter();
        try {
            Response res = new Response();
            res.setSuccess(dao.deleteById(classroomId,studentId));
            out.println(objectMapper.writeValueAsString(res));
            System.out.println("Delete successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
