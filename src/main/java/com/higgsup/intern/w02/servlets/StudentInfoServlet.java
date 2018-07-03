package com.higgsup.intern.w02.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.testcases.management_student.Read;
import com.higgsup.intern.w02.testcases.management_student_info.StudentDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        StudentDetail studentDetail = new StudentDetail();
        PrintWriter out = resp.getWriter();
        int id =Integer.parseInt( req.getParameter("id"));
        String jsonInString = mapper.writeValueAsString(studentDetail.getInfoStudent(id));
        out.println(jsonInString);
    }
}
