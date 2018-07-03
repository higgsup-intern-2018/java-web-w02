package com.higgsup.intern.w02.model;

public class ClassroomInfo extends Classroom {
    private String instructorName;
    private int sum_Student;

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public void setSum_Student(int sum_Student) {
        this.sum_Student = sum_Student;
    }

    public int getSum_Student() {
        return sum_Student;
    }
}
