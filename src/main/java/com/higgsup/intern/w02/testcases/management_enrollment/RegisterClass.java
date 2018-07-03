package com.higgsup.intern.w02.testcases.management_enrollment;

import com.higgsup.intern.w02.entities.Enrollment;
import com.higgsup.intern.w02.entities.Student;
import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.*;

public class RegisterClass {
    public boolean insertClass(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment(student_id, classroom_id) VALUES (?,?)";
        ResultSet keys = null;
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getClassroomId());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                enrollment.setId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
}
