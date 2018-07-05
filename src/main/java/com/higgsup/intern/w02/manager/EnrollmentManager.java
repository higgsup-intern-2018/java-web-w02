package com.higgsup.intern.w02.manager;

import com.higgsup.intern.w02.model.Enrollment;
import com.higgsup.intern.w02.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentManager {

    public boolean insertEnrollment(Enrollment enrollment) throws Exception {
        ResultSet rs = null;
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement stmt = con.prepareStatement("INSERT INTO enrollment(student_id,classroom_id) vales (?,?);");
        ) {
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getClassroomId());
            int affected = stmt.executeUpdate();
            if (affected ==1) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                int newKeys = rs.getInt(1);
                enrollment.setId(newKeys);
            } else {
                System.err.println("Error");
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        } finally {
            if (rs != null) rs.close();
        }
        return true;
    }

    //DELETE
    public boolean deleteEnrollment(int id) throws Exception {
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM enrollment WHERE id =?; ");
        ) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();

            if (affected ==1) {
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
