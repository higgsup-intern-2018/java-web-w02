package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.connection.DBConnection;
import com.higgsup.intern.w02.model.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomDAO
{
    public Classroom displayClassroomById(int classroomId)
    {
        String sql = "SELECT id, name, description FROM classroom WHERE id = " + classroomId;
        ResultSet rs;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(classroomId);
                classroom.setName(rs.getString("name"));
                classroom.setDescription(rs.getString("description"));
                classroom.setInstructorName(getInstructorName(classroomId));
                classroom.setStudentEnroll(countStudent(classroomId));
                return classroom;
            } else {
                return null;
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
            return null;
        }
    }

    public String getInstructorName(int classroomId)
    {
        String sql = "SELECT instructor.name FROM instructor INNER JOIN classroom ON instructor.id = classroom.instructor_id WHERE classroom.id = " + classroomId;
        try
        {
            String s = "";
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()) {
                s = rs.getString("name");
            }
            return s;
        }catch(SQLException e)
        {
            System.err.println(e);
            return null;
        }
    }

    public int countStudent(int classroomId)
    {
        String sql = "SELECT student_id FROM enrollment WHERE classroom_id = " + classroomId;
        try
        {
            int count = 0;
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                count += 1;
            }
            return count;
        }catch(SQLException e)
        {
            System.err.println(e);
            return -1;
        }
    }
}
