package com.higgsup.intern.w02.testcases.management_student;

import com.higgsup.intern.w02.entities.Student;
import com.higgsup.intern.w02.manager.DBUtil;

import java.sql.*;

public class Insert {
    public boolean insertStudent(Student student) throws Exception {
        String sql = "INSERT INTO `student`(`name`, `year_of_birth`, `address`) " +
                "VALUES (?,?,?)";
        ResultSet keys = null;
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYearBirth());
            stmt.setString(3, student.getAddress());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                student.setId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) keys.close();
        }
        return true;
    }
}
