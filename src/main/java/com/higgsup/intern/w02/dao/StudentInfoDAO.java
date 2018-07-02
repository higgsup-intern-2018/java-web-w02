package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.model.StudentInfo;
import com.higgsup.intern.w02.util.DBUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoDAO implements Serializable {
    public List<StudentInfo> displayStudentInfo() throws SQLException {

        List<StudentInfo> studentInfoList = new ArrayList<>();
        String sql = "SELECT student.id, student.name, student.year_of_birth, student.address, classroom.name FROM (enrollment " +
                "INNER JOIN student ON student.id = enrollment.student_id)" +
                "INNER JOIN classroom ON enrollment.classroom_id = classroom.id ";
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            System.out.println("Display information of students");
            while (rs.next()) {
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setId(rs.getInt("id"));
                studentInfo.setName(rs.getString("name"));
                studentInfo.setYearOfBirth(rs.getInt("year_of_birth"));
                studentInfo.setAddress(rs.getString("address"));
                studentInfo.setClassName(rs.getString("classroom.name"));
                studentInfoList.add(studentInfo);
            }
        }
        return studentInfoList;
    }
}
