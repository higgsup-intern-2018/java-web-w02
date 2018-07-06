package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.ClassroomInfo;
import com.higgsup.intern.w02.util.DBUtil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassroomInfoDAO implements Serializable {
    public ClassroomInfo getClassroomInfo(int id) throws SQLException {
        ClassroomInfo classroomInfo = new ClassroomInfo();
        String sql = "SELECT classroom.*, enrollment.*, instructor.*, count(classroom_id) FROM (classroom " +
                "INNER JOIN enrollment ON classroom.id = enrollment.classroom_id)" +
                "INNER JOIN instructor ON classroom.instructor_id = instructor.id " +
                "WHERE classroom.id =" + id;
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println("Display information of the student");
            while (rs.next()) {
                classroomInfo.setId(rs.getInt("id"));
                classroomInfo.setName(rs.getString("name"));
                classroomInfo.setDescription(rs.getString("description"));
                classroomInfo.setInstructorId(rs.getInt("instructor_id"));
                classroomInfo.setInstructorName(rs.getString("instructor.name"));
                classroomInfo.setStudentCount(rs.getInt("count(classroom_id)"));
            }
        }
        return classroomInfo;
    }
}
