package com.higgsup.intern.w02.manager;

import com.higgsup.intern.w02.model.Student;
import com.higgsup.intern.w02.until.DBUntil;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManeger implements Serializable {
//GET
    public List<Student> displayStudent() throws SQLException {
        List<Student> lstStudents = new ArrayList<Student>();
        try (
                Connection conn = DBUntil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM student;");
        ) {
            while (rs.next()) {
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setYearOfBirth(rs.getInt("yearOfBirth"));
                st.setAddress(rs.getString("address"));
            }
        }
        return lstStudents;
    }
    //PUT
    public static Student findById(int stId) throws SQLException {
        ResultSet rs = null;
        try(
                Connection cnn = DBUntil.getConnection();
                PreparedStatement stmt = cnn.prepareStatement("SELECT*FROM student WHERE id=?;")
        ) {
            stmt.setInt(1,stId);
            rs = stmt.executeQuery();
            if (rs.next()){
                Student student = new Student();
                student.setId(stId);
                student.setName(rs.getString("name"));
                student.setYearOfBirth(rs.getInt("yearOfBirth"));
                student.setAddress(rs.getString("address"));
                return student;
            }else {
                return null;
            }
        }catch (SQLException ex){
            System.err.println(ex);
            return null;
        }finally {
            if (rs !=null){
                rs.close();
            }
        }

    }
    public boolean update(Student student) throws Exception{
        try (
                Connection cnn = DBUntil.getConnection();
                PreparedStatement stmt = cnn.prepareStatement("UPDATE student SET name_student=?,year_of_birth=?,adress=? WHERE id=?;");
        )
        {
            stmt.setString(1,student.getName());
            stmt.setInt(2,student.getYearOfBirth());
            stmt.setString(3,student.getAddress());
            int affected  = stmt.executeUpdate();
            if (affected>0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException ex){
            System.err.println(ex);
            return false;
        }
    }
    //POST
    public boolean insert(Student student) throws Exception{
        ResultSet rs = null;
        try (
                Connection conn = DBUntil.getConnection();
                PreparedStatement stmt =conn.prepareStatement("INSERT INTO student (name_student,year_of_birth,address) VALUES (?,?,?);");
        )
        {
            stmt.setString(1,student.getName());
            stmt.setInt(2,student.getYearOfBirth());
            stmt.setString(3,student.getAddress());
            int affected  = stmt.executeUpdate();
            if (affected >0){
                rs = stmt.getGeneratedKeys();
                rs.next();
                int newRs = rs.getInt(1);
                student.setId(newRs);
            }
            else
            {
                System.err.println("Erro");
                return false;
            }
        }catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (rs != null) rs.close();
        }
        return true;
    }
    //DELETE
    public boolean delete(int id) throws Exception{
        try (
                Connection con = DBUntil.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM student WHERE id =?; ");
        ){
            stmt.setInt(1,id);
            int affected  = stmt.executeUpdate();

            if (affected > 0) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}

