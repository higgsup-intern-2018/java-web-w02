package com.higgsup.intern.w02.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.Manager.ManagerEnrollment;
import com.higgsup.intern.w02.Model.Enrollment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollmentServlet extends HttpServlet{
    ObjectMapper mapper = new ObjectMapper();
    ManagerEnrollment me  = new ManagerEnrollment();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        int classroomId = Integer.parseInt(req.getParameter("classroom_id"));
        int studentId = Integer.parseInt(req.getParameter("student_id"));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent_id(studentId);
        enrollment.setClassroom_id(classroomId);

        String jsonInString = mapper.writeValueAsString(me.insert(enrollment));
        out.print(jsonInString);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int cId = Integer.parseInt(request.getParameter("classRoomId"));;
        int sId = Integer.parseInt(request.getParameter("studentId"));

        PrintWriter out = response.getWriter();
        String jsonInString = mapper.writeValueAsString(me.delete(cId, sId));
        out.print(jsonInString);

    }
}
