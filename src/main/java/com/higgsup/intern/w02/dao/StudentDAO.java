package com.higgsup.intern.w02.dao;

import com.higgsup.intern.w02.model.Student;

import java.util.List;

public interface StudentDAO {
    public void addStudent(Student student);
    public void updateStudent(Student student);
    public void deleteStudent(int studentId);
    public List<Student> getAllStudents();
    public Student getStudentById(int studentId);
}
