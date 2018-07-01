package com.higgsup.intern.w02.controller;

import com.higgsup.intern.w02.dao.EnrollmentDAO;
import com.higgsup.intern.w02.model.Enrollment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnrollmentController extends HttpServlet {
    private EnrollmentDAO dao;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int classroomId = Integer.parseInt(request.getParameter("classroomId"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setClassroomId(classroomId);

        try {
            dao.insert(enrollment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
