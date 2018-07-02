package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.Enrollment;
import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public List<Enrollment> displayAll() throws SQLException {

        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT id, classroom_id, student_id FROM enrollment";
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            System.out.println("Display list of enrollments");
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();

                enrollment.setId(rs.getInt("id"));
                enrollment.setClassroomId(rs.getInt("classroom_id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollments.add(enrollment);
            }
        }
        return enrollments;
    }

    public boolean insert(Enrollment enrollment) throws Exception {

        String sql = "INSERT INTO enrollment (student_id, classroom_id ) VALUES (?, ?)";
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
                System.err.println("No rows affected.");
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

    public boolean deleteById(int classroomId, int studentId) throws Exception {

        String sql = "DELETE FROM enrollment WHERE classroom_id = ? AND student_id = ? ";
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
