package com.higgsup.intern.w02.testcases.management_student;

import com.higgsup.intern.w02.entities.Student;
import com.higgsup.intern.w02.servlets.StudentServlet;
import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Read {
    public List<Student> readInfoStudent(){
        List listStudent = new ArrayList<>();
        try (
             Connection conn = DBUtil.getConnection();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM student");
        ) {
            while (rs.next()) {
                Student std = new Student();
                int idStudent = rs.getInt("id");
                std.setId(idStudent);
                String nameStudent = rs.getString("name");
                std.setName(nameStudent);
                int yearbirth = rs.getInt("year_of_birth");
                std.setYearBirth(yearbirth);
                String address = rs.getString("address");
                std.setAddress(address);

                listStudent.add(std);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return listStudent;
    }

    public static void main(String[] args) {
        Read read = new Read();
        read.readInfoStudent();
    }
}
