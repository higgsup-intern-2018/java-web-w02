package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.util.DBUtil;

import java.sql.*;

public class StudentDAO {
    public static void displayAll() throws SQLException {
        String sql = "SELECT id, name, year_of_birth, address FROM student";
        try (
                Connection conn = DBUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            System.out.println("Admin Table:");
            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
                bf.append(rs.getInt("id") + ": ");
                bf.append(rs.getString("name") + ", ");
                bf.append(rs.getInt("year_of_birth" + ", "));
                bf.append(rs.getString("address"));
                System.out.println(bf.toString());
            }
        }
    }

    public static Student findById(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE id = ?";
        ResultSet rs = null;

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setYearOfBirth(rs.getInt("year_of_birth"));
                student.setAddress(rs.getString("address"));
                return student;
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

    public static boolean insert(Student student) throws Exception {

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

    public static boolean update(Student student) throws Exception {

        String sql =
                "UPDATE student SET name = ?, year_of_birth = ?, address = ? WHERE id = ?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getYearOfBirth());
            stmt.setString(3, student.getAddress());


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

    public static boolean deleteById(int id) throws Exception {

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
