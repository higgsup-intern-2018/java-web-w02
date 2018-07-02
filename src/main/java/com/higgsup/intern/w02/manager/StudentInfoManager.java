package com.higgsup.intern.w02.manager;

import com.higgsup.intern.w02.model.Classroom;
import com.higgsup.intern.w02.model.StudentInfo;
import com.higgsup.intern.w02.until.DBUntil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoManager implements Serializable {
    //GET - Student information
    public StudentInfo displayStudentInfo(int id) throws SQLException {
        StudentInfo studentInfo = new StudentInfo();
        String sql = "select student.* , classroom.*, enrollment.* " +
                "from enrollment " +
                "inner join student on enrollment.student_id = student.id " +
                "inner join classroom on enrollment.classroom_id = classroom.id "+
                "where student.id = "+id;
        try (
                Connection conn = DBUntil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            List<Classroom> classroomList = new ArrayList<>();
            while (rs.next()) {
                studentInfo.setId(rs.getInt("id"));
                studentInfo.setName(rs.getString("name_student"));
                studentInfo.setYearOfBirth(rs.getInt("year_of_birth"));
                studentInfo.setAddress(rs.getString("address"));
                int classroomId = rs.getInt("enrollment.classroom_id");
                String  classroomName = rs.getString("classroom.name_class");
                String  classroomDescription = rs.getString("classroom.description");
                int  instructorId = rs.getInt("classroom.instructor_id");
                Classroom classroom = new Classroom();
                classroom.setId(classroomId);
                classroom.setNameClass(classroomName);
                classroom.setDescribe(classroomDescription);
                classroom.setinstructorId(instructorId);
                classroomList.add(classroom);
            }
            studentInfo.setListClass(classroomList);
        }
        return studentInfo;
    }
}
