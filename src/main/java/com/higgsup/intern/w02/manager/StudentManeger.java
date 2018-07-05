package com.higgsup.intern.w02.manager;


import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.util.DBUtil;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManeger implements Serializable {
    //GET
    public List<Student> displayStudent() throws SQLException {
        List<Student> lstStudents = new ArrayList<Student>();
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM student;");
        ) {
            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name_student"));
                st.setYearOfBirth(rs.getInt("year_of_birth"));
                st.setAddress(rs.getString("address"));
                lstStudents.add(st);
            }
        }
        return lstStudents;
    }

    //PUT
    public boolean updateStudent(Student student, int id) throws Exception {
        try (
                Connection cnn = DBUtil.getConnection();
                PreparedStatement stmt = cnn.prepareStatement("UPDATE student SET name_student=?,year_of_birth=?,address = ? WHERE id=?;");
        ) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYearOfBirth());
            stmt.setString(3, student.getAddress());
            stmt.setInt(4, id);
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }

    //POST
    public boolean insertStudent(Student student) throws Exception {
        ResultSet key = null;
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (name_student,year_of_birth,address) VALUES (?,?,?);");
        ) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYearOfBirth());
            stmt.setString(3, student.getAddress());
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                student.setId(newKey);
            } else {
                System.err.println("Error.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (key != null) key.close();
        }
        return true;
    }

    //DELETE
    public boolean deleteStudent(int id) throws Exception {
        try (
                Connection con = DBUtil.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM student WHERE id =?; ");
        ) {
            stmt.setInt(1, id);
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

