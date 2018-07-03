package com.higgsup.intern.w02.Manager;

import com.higgsup.intern.w02.Model.Student;
import com.higgsup.intern.w02.Util.StudentConnection;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerStudent{

    public List<Student> displayAll() throws SQLException{
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, name, year_of_birth, address FROM student";
        try {
            Connection conn = StudentConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) { // kiểm tra kết quả từng hàng
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setYear_of_birth(rs.getInt("year_of_birth"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return students;
    }

    // hàm thêm sinh viên mới
    public static boolean insertStudent(Student student) throws Exception{
        String sql = "INSERT into student ( name , year_of_birth, address) Values (?, ?, ?)";
        ResultSet keys = null;
        try(
                Connection conn = StudentConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ) {
                stmt.setString(1,student.getName());
                stmt.setInt(2,student.getYear_of_birth());
                stmt.setString(3,student.getAddress());
                int affected = stmt.executeUpdate();
                if(affected == 1){
                    keys = stmt.getGeneratedKeys();
                    keys.next();
                    int newKey = keys.getInt(1);
                    student.setId(newKey);
                }
                else {
                    System.err.println("No row affected");
                    return false;
                }
        }catch (Exception e){
            System.err.println(e);
            return false;
        }finally {
            if (keys != null)
                keys.close();
        }
        return true;
    }

    // hàm sửa thông tin sinh viên
    public static boolean update(Student student ) throws Exception{
        String sql = "UPDATE student SET name = ? , year_of_birth = ?, address=? WHERE id = ?";
        try(
                Connection conn = StudentConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getYear_of_birth());
            stmt.setString(4,student.getAddress());

            int affected = stmt.executeUpdate();
            if (affected == 1){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }

    // hàm xóa sinh viên theo mã
    public  boolean deleteById(int id) throws Exception{
        String sql = "DELETE FROM student WHERE id = ? ";
        try(
            Connection conn = StudentConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
                ){
                    stmt.setInt(1 , id);
                    int affected = stmt.executeUpdate();

                    if(affected ==1){
                        return true;
                    }else {
                        return false;
                    }
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }
    }

    public Student displayStudentbyId(int id) throws SQLException{
        String sql = "SELECT * FROM student WHERE id = ?";
        ResultSet rs = null;
        try{
            Connection conn = StudentConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if(rs.next()){
                Student student = new Student();
                student.setId(id);
                student.setName(rs.getString("name"));
                student.setYear_of_birth(rs.getInt("year_of_birth"));
                student.setAddress(rs.getString("address"));
                return student;
            }else {
                return null;
            }
        }catch (SQLException e){
            System.err.println(e);
            return null;
        }finally {
            if(rs != null){
                rs.close();
            }
        }
    }

}
