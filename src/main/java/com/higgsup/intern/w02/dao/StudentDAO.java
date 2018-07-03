package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.connection.DBConnection;
import com.higgsup.intern.w02.model.Classroom;
import com.higgsup.intern.w02.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO
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

    //Tạo sinh viên
    public boolean insertStudent(Student student){
        String sql = "INSERT INTO student(name, year_of_birth, address) VALUES (?, ?, ?)";
        ResultSet keys = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getBirthYear());
            ps.setString(3, student.getAddress());
            int affected = ps.executeUpdate();
            if (affected == 1) {
                keys = ps.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                student.setId(newKey);
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

    //Tìm sinh viên theo id
    public Student findById(int id)
    {
        String sql = "SELECT * FROM student WHERE id = " + id;
        ResultSet rs;
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
            {
                Student s = new Student();
                s.setId(id);
                s.setName(rs.getString("name"));
                s.setBirthYear(rs.getInt("year_of_birth"));
                s.setAddress(rs.getString("address"));
                s.setClassroomList(displayClassroom(id));
                return s;
            }else {
                return null;
            }
        }catch(SQLException e)
        {
            System.err.println(e);
            return null;
        }
    }

    //Sửa thông tin
    public boolean updateStudent(int id, Student student){
        String sql = "UPDATE student SET name = ?, year_of_birth = ?, address = ? WHERE id = ?";
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            Student s = findById(id);
            if(s != null) {
                ps.setString(1, student.getName());
                ps.setInt(2, student.getBirthYear());
                ps.setString(3, student.getAddress());
                ps.setInt(4, id);
                int affected = ps.executeUpdate();
                if (affected == 1) {
                    setSuccess(true);
                } else {
                    setSuccess(false);
                }
            }else
                {
                    setSuccess(false);
                }
        }
        catch (SQLException e) {
            System.err.println(e);
            setSuccess(false);
        }
        return getSuccess();
    }

    //Xóa sinh viên
    public boolean deleteStudent(int studentId){
        String sql = "DELETE FROM student WHERE id = ?";
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
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

    //Trả về tất cả danh sách sinh viên
    public List<Student> displayAll()
    {
        List<Student> students = new ArrayList<>();

        try
        {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name, year_of_birth, address FROM student";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Student list: ");
            while(rs.next())
            {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setBirthYear(rs.getInt("year_of_birth"));
                s.setAddress(rs.getString("address"));
                students.add(s);
            }
            rs.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
        }
        return students;
    }

    public List<Classroom> displayClassroom(int studentId)
    {
        List<Classroom> classrooms = new ArrayList<>();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        String sql = "SELECT classroom.* FROM (enrollment INNER JOIN student ON student.id = enrollment.student_id) " +
                "INNER JOIN classroom ON enrollment.classroom_id = classroom.id WHERE student.id = " + studentId;
        try
        {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps =connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next())
            {
                Classroom c = new Classroom();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setInstructorName(classroomDAO.getInstructorName(rs.getInt("id")));
                c.setStudentEnroll(classroomDAO.countStudent(rs.getInt("id")));
                classrooms.add(c);
            }
            rs.close();
        }
        catch(SQLException e)
        {
            System.err.println(e);
        }
        return classrooms;
    }

}
