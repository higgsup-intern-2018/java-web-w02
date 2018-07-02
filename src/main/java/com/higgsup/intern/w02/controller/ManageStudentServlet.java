package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.StudentDAO;
import com.higgsup.intern.w02.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ManageStudentServlet extends HttpServlet
{
    ObjectMapper mapper = new ObjectMapper();
    StudentDAO studentDAO = new StudentDAO();

    //get all student
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        PrintWriter out = response.getWriter();
        String jsonInString = mapper.writeValueAsString(studentDAO.displayAll());
        out.print(jsonInString);
    }

    //create student
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        StringBuffer jb = new StringBuffer();
        String line = null;
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            Student s = mapper.readValue(String.valueOf(jb), Student.class);
            String string = mapper.writeValueAsString(studentDAO.insertStudent(s));
            out.print(string);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Update student with id
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int studentId = Integer.parseInt(req.getParameter("id"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        PrintWriter out = resp.getWriter();
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            Student s = mapper.readValue(String.valueOf(sb), Student.class);
            String string = mapper.writeValueAsString(studentDAO.updateStudent(studentId, s));
            out.print(string);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Delete student with id
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentId = request.getParameter("id");
        int id = Integer.parseInt(studentId);
        PrintWriter out = response.getWriter();
        String jsonInString = mapper.writeValueAsString(studentDAO.deleteStudent(id));
        out.print(jsonInString);
    }
}