package com.higgsup.intern.w02.testcases.management_enrollment;

import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public Boolean detele(int classroomId, int studentId) {
        String sql = "DELETE FROM enrollment WHERE classroom_id = ? AND student_id = ?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, classroomId);
            stmt.setInt(2, studentId);
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
