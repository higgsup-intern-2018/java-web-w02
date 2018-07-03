package com.higgsup.intern.w02.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.dao.ClassroomDAO;
import com.higgsup.intern.w02.dao.StudentDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ManageClassroomServlet extends HttpServlet
{
    //Get classroom information through id
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        ClassroomDAO classroomDAO = new ClassroomDAO();

        int classroomId = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        String jsonInString = mapper.writeValueAsString(classroomDAO.displayClassroomById(classroomId));
        out.print(jsonInString);
    }
}
