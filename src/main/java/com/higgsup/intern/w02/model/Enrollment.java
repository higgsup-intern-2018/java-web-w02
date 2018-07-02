package com.higgsup.intern.w02.model;

public class Enrollment
{
    private int id;
    private int student_id;
    private int classroom_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public int getClassroomId() {
        return classroom_id;
    }

    public void setClassroomId(int classroom_id) {
        this.classroom_id = classroom_id;
    }
}
