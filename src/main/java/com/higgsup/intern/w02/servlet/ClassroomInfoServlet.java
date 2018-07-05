package com.higgsup.intern.w02.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.manager.ClassroomInfoManager;
import com.higgsup.intern.w02.manager.StudentInfoManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class ClassroomInfoServlet extends HttpServlet {
    private ClassroomInfoManager mn;
   private ObjectMapper obm = new ObjectMapper();

    public ClassroomInfoServlet() {
        super();
        mn = new ClassroomInfoManager();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            out.println(obm.writeValueAsString(mn.displayClassroomInfo(id)));
            System.out.println("Display successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
