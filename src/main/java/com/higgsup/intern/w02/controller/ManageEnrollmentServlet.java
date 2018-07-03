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

public class ManageEnrollmentServlet extends HttpServlet
{
    ObjectMapper mapper = new ObjectMapper();
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    Response res = new Response();

    //New enrollment
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out = resp.getWriter();
        int classroomId = Integer.parseInt(req.getParameter("classRoomId"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setClassroomId(classroomId);
        res.setSuccess(enrollmentDAO.insertEnrollment(enrollment));
        String jsonInString = mapper.writeValueAsString(res);
        out.print(jsonInString);
    }

    //Unenroll
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cId = Integer.parseInt(req.getParameter("classRoomId"));;
        int sId = Integer.parseInt(req.getParameter("studentId"));
        PrintWriter out = resp.getWriter();
        res.setSuccess(enrollmentDAO.deleteEnrollment(cId, sId));
        String jsonInString = mapper.writeValueAsString(res);
        out.print(jsonInString);
    }
}
