package com.higgsup.intern.w02.Manager;

import com.higgsup.intern.w02.Model.Enrollment;
import com.higgsup.intern.w02.Util.StudentConnection;

import java.sql.*;

public class ManagerEnrollment {
    public boolean insert(Enrollment enrollment){
        String sql = "INSERT INTO enrollment(student_id , classroom_id) VALUES (? , ?)";
        ResultSet rs = null;
        try(
                Connection conn = StudentConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
                ){
            ps.setInt(1,enrollment.getStudent_id());
            ps.setInt(2,enrollment.getStudent_id());

            int affected = ps.executeUpdate();

            if(affected ==1){
                rs = ps.getGeneratedKeys();
                rs.next();
                int newKey = rs.getInt(1);
                enrollment.setId(newKey);
            }else {
                System.err.println("No rows affected.");
                return false;
            }
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }
        return true;
    }

    public boolean delete(int student_id, int classroom_id ){
        String sql = "DELETE FROM enrollment WHERE student_id = ? AND classroom_id = ?";
        try
        {
            Connection connection = StudentConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, student_id);
            ps.setInt(2, classroom_id);
            int affected = ps.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(SQLException e)
        {
            System.err.println(e);
            return false;
        }

    }
}
