package com.higgsup.intern.w02.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.intern.w02.Manager.ManagerStudent;
import com.higgsup.intern.w02.Model.Response;
import com.higgsup.intern.w02.Model.Student;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.sql.SQLException;


public class StudentServlet extends HttpServlet  {
    ObjectMapper mapper = new ObjectMapper();
    ManagerStudent ms = new ManagerStudent();

    // trả về danh sách tất cả sinh viên
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        String jsonInString = null;
        try {
            jsonInString = mapper.writeValueAsString(ms.displayAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print(jsonInString);
    }

    //Tạo sinh viên với các thông tin được truyền lên thông qua request body.
    // (mã sinh viên được tự động generate theo thứ tự tăng dần)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        StringBuffer sb = new StringBuffer();
        String line = null;
        PrintWriter out = response.getWriter();
        try{
            BufferedReader reader = request.getReader(); // đọc file
            while ((line = reader.readLine()) != null){
                sb.append(line); // nối chuỗi
            }
            Student student = mapper.readValue(String.valueOf(sb), Student.class); //JSON from String to Object
            String string = mapper.writeValueAsString(ManagerStudent.insertStudent(student));
            out.print(string);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Sửa thông tin sinh viên có mã là param id được truyền lên theo request
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int studentId = Integer.parseInt(request.getParameter("id"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        PrintWriter out = response.getWriter();
        try{
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
            Student student = mapper.readValue(String.valueOf(sb), Student.class);
            String string = mapper.writeValueAsString(ManagerStudent.update(student));
            out.print(string);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Xóa sinh viên có mã là id được truyền lên từ param
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String studentId = request.getParameter("id");
        int id = Integer.parseInt(studentId);
        PrintWriter out = response.getWriter();
        try {
            Response res = new Response();
            res.setSuccess(ms.deleteById(id));
            out.print(mapper.writeValueAsString(res));
            String jsonInString = mapper.writeValueAsString(ms.deleteById(id));
            out.print(jsonInString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


