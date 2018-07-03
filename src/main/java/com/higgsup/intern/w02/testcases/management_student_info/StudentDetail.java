package com.higgsup.intern.w02.testcases.management_student_info;

import com.higgsup.intern.w02.entities.Student;
import com.higgsup.intern.w02.entities.StudentInfo;
import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDetail {
    public StudentInfo getInfoStudent(int stdID) {
        StudentInfo studentInfo = new StudentInfo();
        String sql = "SELECT student.id, student.name, student.year_of_birth, student.address, classroom.name " +
                    "FROM (enrollment INNER JOIN student ON student.id = enrollment.student_id)" +
                    "INNER JOIN classroom ON enrollment.classroom_id = classroom.id " +
                    "WHERE student.id =" + stdID;
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {

                int idStudent = rs.getInt("id");
                studentInfo.setId(idStudent);
                String studentName = rs.getString("name");
                studentInfo.setStudentName(studentName);
                int yearbirth = rs.getInt("year_of_birth");
                studentInfo.setYearBirth(yearbirth);
                String address = rs.getString("address");
                studentInfo.setAddress(address);
                String className = rs.getString("classroom.name");
                studentInfo.setClassName(className);
                }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return studentInfo;
    }
}
