package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.Classroom;
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
    public StudentInfo getStudentInfo(int id) throws SQLException {
        StudentInfo studentInfo = new StudentInfo();
        String sql = "SELECT student.*, enrollment.*, classroom.* FROM  student " +
                "LEFT JOIN enrollment ON student.id = enrollment.student_id " +
                "LEFT JOIN classroom ON enrollment.classroom_id = classroom.id " +
                "WHERE student.id = " + id;
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println("Display information of the student");

            List<Classroom> classroomList = new ArrayList<>();

            while (rs.next()) {
                studentInfo.setId(rs.getInt("id"));
                studentInfo.setName(rs.getString("name"));
                studentInfo.setYearOfBirth(rs.getInt("year_of_birth"));
                studentInfo.setAddress(rs.getString("address"));

                int classroomId = rs.getInt("enrollment.classroom_id");
                String name = rs.getString("classroom.name");
                String ClassroomDescription = rs.getString("classroom.description");
                int instructorId = rs.getInt("classroom.instructor_id");

                Classroom classroom = new Classroom();
                classroom.setId(classroomId);
                classroom.setName(name);
                classroom.setDescription(ClassroomDescription);
                classroom.setInstructorId(instructorId);

                classroomList.add(classroom);
            }

            studentInfo.setClassroomList(classroomList);
        }
        return studentInfo;
    }
}
