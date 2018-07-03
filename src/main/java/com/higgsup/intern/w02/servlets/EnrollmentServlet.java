package com.higgsup.intern.w02.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.entities.Enrollment;
import com.higgsup.intern.w02.testcases.management_enrollment.Delete;
import com.higgsup.intern.w02.testcases.management_enrollment.RegisterClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollmentServlet extends HttpServlet {
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Delete delete = new Delete();
        PrintWriter out = resp.getWriter();
        int classroomID =Integer.parseInt( req.getParameter("classRoomId"));  //truyen param
        int studentID =Integer.parseInt( req.getParameter("studentId"));
        String jsonInString = mapper.writeValueAsString(delete.detele(classroomID,studentID));
        out.println(jsonInString);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        RegisterClass register = new RegisterClass();
        Enrollment enrollment = new Enrollment();
        int classroomID =Integer.parseInt( req.getParameter("classRoomId"));  //truyen param
        int studentID =Integer.parseInt( req.getParameter("studentId"));
        enrollment.setStudentId(studentID);
        enrollment.setClassroomId(classroomID);
        String jsonInString = mapper.writeValueAsString(register.insertClass(enrollment));
        out.println(jsonInString);
    }
}
