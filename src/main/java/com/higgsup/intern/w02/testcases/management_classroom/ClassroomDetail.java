package com.higgsup.intern.w02.testcases.management_classroom;

import com.higgsup.intern.w02.entities.Classroom;
import com.higgsup.intern.w02.entities.StudentInfo;
import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassroomDetail {

    public Classroom getInfoClassroom(int classID) {
        Classroom classroom = new Classroom();
        String sql = "SELECT classroom.*, enrollment.*, instructor.*, count(classroom_id) " +
                "FROM (classroom INNER JOIN enrollment ON classroom.id = enrollment.classroom_id)" +
                "INNER JOIN instructor ON classroom.instructor_id = instructor.id " +
                "WHERE classroom.id =" + classID;
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {

                int id = rs.getInt("id");
                classroom.setClassID(id);

                String className = rs.getString("name");
                classroom.setClassName(className);

                String description = rs.getString("description");
                classroom.setDescription(description);

                String instructorName = rs.getString("instructor.name");
                classroom.setInstructorName(instructorName);

                int studentCount = rs.getInt("count(classroom_id)");
                classroom.setStudentCount(studentCount);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return classroom;
    }
}
