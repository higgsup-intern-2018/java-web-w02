package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.util.DBUtil;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements Serializable {
    public List<Student> getStudentList() throws SQLException {

        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, year_of_birth, address FROM student";
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            System.out.println("Display list of students");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setYearOfBirth(rs.getInt("year_of_birth"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        }
        return students;
    }

    public boolean insert(Student student) throws Exception {

        String sql = "INSERT INTO student (name, year_of_birth, address ) VALUES (?, ?, ?)";
        ResultSet keys = null;
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYearOfBirth());
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

    public boolean update(Student student, int id) throws Exception {

        String sql =
                "UPDATE student SET name = ?, year_of_birth = ?, address = ? WHERE id = ?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
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

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    public boolean deleteById(int id) throws Exception {

        String sql = "DELETE FROM student WHERE id = ?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        )
        {
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
