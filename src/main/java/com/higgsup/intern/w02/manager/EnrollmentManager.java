package com.higgsup.intern.w02.manager;

import com.higgsup.intern.w02.model.Classroom;
import com.higgsup.intern.w02.model.Enrollment;
import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.until.DBUntil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentManager {

    public boolean insertEnrollment(Enrollment enrollment) throws Exception {
        ResultSet rs = null;
        try (
                Connection con = DBUntil.getConnection();
                PreparedStatement stmt = con.prepareStatement("INSERT INTO enrollment vales (?,?,?);");
        ) {
            stmt.setInt(1, enrollment.getId());
            stmt.setInt(2, enrollment.getId());
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                rs = stmt.getGeneratedKeys();
                rs.next();
                int newKeys = rs.getInt(0);
                enrollment.setId(newKeys);
            } else {
                System.err.println("Erro");
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
                Connection con = DBUntil.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM enrollment WHERE id =?; ");
        ) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();

            if (affected > 0) {
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
