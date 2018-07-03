package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.StudentDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentInfoServlet extends HttpServlet
{
    ObjectMapper mapper = new ObjectMapper();
    StudentDAO studentDAO = new StudentDAO();

    //Get student information through id
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        PrintWriter out = response.getWriter();
        int studentId = Integer.parseInt(request.getParameter("id"));
        String jsonInString = mapper.writeValueAsString(studentDAO.findById(studentId));
        out.print(jsonInString);
    }

}
