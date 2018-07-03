package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.connection.DBConnection;
import com.higgsup.intern.w02.model.Enrollment;

import java.sql.*;

public class EnrollmentDAO
{
    private boolean success;

    public boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    //Đăng ký lớp học
    public boolean insertEnrollment(Enrollment enrollment){
        String sql = "INSERT INTO enrollment(student_id, classroom_id) VALUES(?,?)";
        ResultSet keys = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getClassroomId());
            int affected = ps.executeUpdate();
            if (affected == 1) {
                keys = ps.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                enrollment.setId(newKey);
            } else {
                System.err.println("No rows affected");
                setSuccess(false);
            }
        } catch (SQLException e) {
            System.err.println(e);
            setSuccess(false);
        }
        setSuccess(true);
        return getSuccess();
    }

    //Hủy đăng ký lớp học
    public boolean deleteEnrollment(int classroomId, int studentId)
    {
        String sql = "DELETE FROM enrollment WHERE student_id = ? AND classroom_id = ?";
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, classroomId);
            int affected = ps.executeUpdate();

            if (affected == 1) {
                setSuccess(true);
            } else {
                setSuccess(false);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e);
            setSuccess(false);
        }
        return getSuccess();
    }
}
