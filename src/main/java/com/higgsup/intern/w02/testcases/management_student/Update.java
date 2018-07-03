package com.higgsup.intern.w02.testcases.management_student;

import com.higgsup.intern.w02.entities.Student;
import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    public boolean updateInfoStudent(int id,Student student) throws Exception {

        String sql = "UPDATE `student` SET name = ?, year_of_birth = ?, address = ? WHERE id = "+id;
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYearBirth());
            stmt.setString(3, student.getAddress());

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
