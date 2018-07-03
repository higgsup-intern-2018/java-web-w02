package com.higgsup.intern.w02.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.entities.Student;
import com.higgsup.intern.w02.manager.Respone;
import com.higgsup.intern.w02.testcases.management_student.Delete;
import com.higgsup.intern.w02.testcases.management_student.Insert;
import com.higgsup.intern.w02.testcases.management_student.Read;
import com.higgsup.intern.w02.testcases.management_student.Update;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;

public class StudentServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer sb = new StringBuffer();       // thay doi string
        String line = null;
        PrintWriter out = resp.getWriter();         // n ra kq
        try{
            BufferedReader sr = req.getReader();    //doc file
            while ((line = sr.readLine()) != null){
                sb.append(line);                    //noi chuoi
            }
            Student student = new Student();
            student = mapper.readValue(String.valueOf(sb), Student.class);//JSON from String to Object
            Insert insert = new Insert();
            String jsonInString = mapper.writeValueAsString(insert.insertStudent(student)); // Convert Java object to JSON
            out.println(jsonInString);

        }catch(Exception ex){
          }
    }

    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer sb = new StringBuffer();       // thay doi string
        String line = null;
        PrintWriter out = resp.getWriter();         // in ra kq
        try{
            BufferedReader sr = req.getReader();    //doc file
            while ((line = sr.readLine()) != null){
                sb.append(line);                    //noi chuoi
            }
            Student student = new Student();
            student = mapper.readValue(String.valueOf(sb), Student.class);//JSON from String to Object
            Update update = new Update();
            int id =Integer.parseInt( req.getParameter("id"));  //truyen param
            String jsonInString = mapper.writeValueAsString(update.updateInfoStudent(id,student)); // Convert Java object to JSON
            out.println(jsonInString);
        }catch(Exception ex){
            System.err.println(ex);
        }
    }

     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Read read = new Read();
        PrintWriter out = resp.getWriter();
        String jsonInString = mapper.writeValueAsString(read.readInfoStudent());
        out.println(jsonInString);
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Delete delete = new Delete();
        PrintWriter out = resp.getWriter();
        Respone respone = new Respone();
        int id =Integer.parseInt( req.getParameter("id"));  //truyen param
        respone.setSuccess(delete.deteleStudent(id));
        String jsonInString = mapper.writeValueAsString(respone);
        out.println(jsonInString);
    }
}
