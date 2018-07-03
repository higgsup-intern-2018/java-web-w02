package com.higgsup.intern.w02.Manager;

import com.higgsup.intern.w02.Model.Classroom;
import com.higgsup.intern.w02.Util.StudentConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerClassroom {
    public Classroom displayClassroom(int id) throws SQLException {
        String sql = "SELECT * FROM classroom WHERE id = ?";
        ResultSet rs = null;
        try {
            Connection conn = StudentConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);// thực hiện truy vấn tham số
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(id);
                classroom.setName(rs.getString("name"));
                classroom.setDescription(rs.getString("description"));
                classroom.setInstructor_id(rs.getInt("instructor_id"));
                return classroom;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}


