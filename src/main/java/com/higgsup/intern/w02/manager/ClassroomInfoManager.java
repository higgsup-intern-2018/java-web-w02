package com.higgsup.intern.w02.manager;

import com.higgsup.intern.w02.model.ClassroomInfo;
import com.higgsup.intern.w02.until.DBUntil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassroomInfoManager implements Serializable {

    public ClassroomInfo displayClassroomInfo(int id) throws SQLException {
        ClassroomInfo classroomInfo = new ClassroomInfo();
        String sql = "select classroom.*,instructor.name_instructor, count(classroom.id)" +
                "from enrollment" +
                "inner join student on enrollment.student_id = student.id" +
                "inner join classroom on enrollment.classroom_id = classroom.id" +
                "inner join instructor on classroom.instructor_id=instructor.id" +
                "where classroom.id = " + id;
        try (
                Connection conn = DBUntil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                classroomInfo.setId(rs.getInt("id"));
                classroomInfo.setNameClass(rs.getString("name_class"));
                classroomInfo.setDescription(rs.getString("description"));

            }
            return classroomInfo;
        }
    }
}