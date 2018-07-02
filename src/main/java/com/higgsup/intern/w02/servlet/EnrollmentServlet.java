package com.higgsup.intern.w02.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.manager.EnrollmentManager;
import com.higgsup.intern.w02.model.Enrollment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollmentServlet extends HttpServlet {
    private EnrollmentManager mnEroll;
    ObjectMapper obmEroll = new ObjectMapper();

    public EnrollmentServlet() {
        super();
        mnEroll = new EnrollmentManager();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        PrintWriter out = response.getWriter();
        Enrollment enrollment = obmEroll.readValue(json, Enrollment.class);
        try {
            boolean code = mnEroll.insertEnrollment(enrollment);
            out.println(obmEroll.writeValueAsString(code));
            System.out.println("Insert successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        try {
            boolean code = mnEroll.deleteEnrollment(id);
            out.println(obmEroll.writeValueAsString(code));
            System.out.println("Delete successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
